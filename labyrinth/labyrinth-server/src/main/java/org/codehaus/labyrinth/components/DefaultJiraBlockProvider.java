package org.codehaus.labyrinth.components;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.codehaus.labyrinth.Issue;
import org.codehaus.labyrinth.om.base.BaseProject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.walding.common.util.Base64;
import com.walding.common.util.StreamUtility;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class DefaultJiraBlockProvider implements JiraBlockProvider, Serviceable, Disposable
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER =
        org.apache.log4j.Logger.getLogger(DefaultJiraBlockProvider.class);

    private ServiceManager sm;
    public int getIssueCount(BaseProject project)
    {
        try
        {
            Document document = getIssueDocument(project);
            List l = document.selectNodes("//item");
            return l.size();
        }
        catch (Exception e)
        {
            LOGGER.warn("error loading xml", e);
            throw new RuntimeException(e);
        }
    }

    public List getIssues(BaseProject project)
    {
        try
        {
            Document document = getIssueDocument(project);
            List l = document.selectNodes("//item");

            Iterator iter = l.iterator();
            List results = new ArrayList();
            while (iter.hasNext())
            {
                Element element = (Element) iter.next();

                Issue i = new Issue();
                String id = element.selectSingleNode("key").getText();
                LOGGER.info(id);
                i.setId(id);
                i.setUrl(element.selectSingleNode("link").getText());
                i.setTitle(element.selectSingleNode("title").getText());
                i.setDescription(element.selectSingleNode("description").getText());
                results.add(i);
            }
            return results;
        }
        catch (Exception e)
        {
            LOGGER.warn("error loading xml", e);
            throw new RuntimeException(e);
        }
    }

    public Document getIssueDocument(BaseProject project)
        throws DocumentException, IOException, ClassNotFoundException, ServiceException
    {
        CacheComponent cc = (CacheComponent) sm.lookup(CacheComponent.ROLE);
        String xml = (String) cc.load("project" + project.getProjectCode());

        if (xml == null)
        {
            URL url = new URL(project.getIssueXml());

            URLConnection connection = url.openConnection();
            String password = "waldingb:good1";
            String encodedPassword = Base64.encodeBytes(password.getBytes());
            connection.setRequestProperty("Proxy-Authorization", "Basic " + encodedPassword);

            xml = StreamUtility.readInputStreamIntoString(connection.getInputStream());
            cc.save("project" + project.getProjectCode(), xml);
        }

        SAXReader reader = new SAXReader();
        reader.setEntityResolver(new EntityResolver()
        {
            public InputSource resolveEntity(String arg0, String arg1) throws SAXException, IOException
            {
                // FIXME Auto-generated method stub
                LOGGER.info("resolving entity " + arg0 + " / " + arg1);
                return null;
            }
        });

        StringReader sr = new StringReader(xml);
        Document document = reader.read(sr);
        return document;

    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager arg0) throws ServiceException
    {
        sm = arg0;

    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public void dispose()
    {
        LOGGER.info("Disposing " + getClass().getName());
        sm = null;
    }

}
