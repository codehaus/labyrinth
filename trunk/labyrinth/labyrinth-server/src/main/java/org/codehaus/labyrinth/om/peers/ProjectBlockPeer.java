/*
 * Created on Aug 8, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.peers;

import java.util.Map;

import net.sf.hibernate.HibernateException;

import org.apache.avalon.framework.service.ServiceException;
import org.codehaus.labyrinth.om.ProjectBlock;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public interface ProjectBlockPeer
{
    public static final String ROLE = ProjectBlockPeer.class.getName();

    public Map getProperties(ProjectBlock projectBlock) throws HibernateException, ServiceException;
}
