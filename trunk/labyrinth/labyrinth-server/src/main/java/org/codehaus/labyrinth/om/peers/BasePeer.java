/*
 * Created on Aug 5, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.peers;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.codehaus.labyrinth.components.PersistenceComponent;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class BasePeer implements Serviceable
{
    private ServiceManager serviceManager = null;
    public void service(ServiceManager sm) throws ServiceException
    {
        serviceManager = sm;
    }

    protected ServiceManager getServiceManager()
    {
        return serviceManager;
    }

    protected PersistenceComponent getPersistenceComponent() throws ServiceException
    {
        return (PersistenceComponent) getServiceManager().lookup(PersistenceComponent.ROLE);
    }

}
