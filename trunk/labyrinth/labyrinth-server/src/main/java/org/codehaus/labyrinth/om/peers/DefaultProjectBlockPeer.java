/*
 * Created on Aug 8, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.peers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.type.NullableType;

import org.apache.avalon.framework.service.ServiceException;
import org.codehaus.labyrinth.om.ProjectBlock;
import org.codehaus.labyrinth.om.ProjectBlockProperty;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class DefaultProjectBlockPeer extends BasePeer implements ProjectBlockPeer
{

    public Map getProperties(ProjectBlock projectBlock) throws HibernateException, ServiceException
    {
        Object[] params = { projectBlock.getId()};
        NullableType[] types = { Hibernate.INTEGER, Hibernate.INTEGER };
        Iterator iter =
            getPersistenceComponent().getSession().iterate(
                "SELECT FROM ProjectBlockProperty WHERE rfLProjectBlock = ?",
                params,
                types);
        Map results = new HashMap();
        while (iter.hasNext())
        {
            ProjectBlockProperty pbp = (ProjectBlockProperty) iter.next();
            results.put(pbp.getName(), pbp.getValue());
        }
        return results;
    }

}
