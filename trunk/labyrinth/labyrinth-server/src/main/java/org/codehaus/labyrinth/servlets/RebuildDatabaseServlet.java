/*
 * Created on Aug 7, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.components.DefaultPersistenceComponent;
import org.codehaus.labyrinth.components.PersistenceComponent;

import com.walding.common.util.StringUtil;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class RebuildDatabaseServlet extends LabyrinthServlet
{

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    private String key = StringUtil.randomAlpha(5);

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequestInternal(HttpServletRequest request, HttpServletResponse response, Context context)
        throws Exception
    {
        context.put("key", key);
        if (key.equalsIgnoreCase(request.getParameter("key")))
        {
            PersistenceComponent pc = (PersistenceComponent) getServiceManager().lookup(PersistenceComponent.ROLE);
            if (pc instanceof DefaultPersistenceComponent)
            {
                DefaultPersistenceComponent dpc = (DefaultPersistenceComponent) pc;
                dpc.generateSchema(true, true);
                dpc.loadTest();
            }
            else
            {
                context.put("error", "Bizarro persistence component:" + pc.getClass().getName());
            }
        }
        return getTemplate("velocity/RebuildDatabaseServlet.vm");
    }

}
