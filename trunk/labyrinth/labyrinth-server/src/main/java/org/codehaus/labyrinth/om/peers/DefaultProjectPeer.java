package org.codehaus.labyrinth.om.peers;

import java.util.List;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.components.PersistenceComponent;
import org.codehaus.labyrinth.om.Project;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class DefaultProjectPeer implements ProjectPeer, Serviceable
{

    /* (non-Javadoc)
     * @see org.codehaus.labyrinth.om.peers.ProjectPeer#doSearch(org.codehaus.labyrinth.om.peers.ProjectSearch)
     */
    public List doSearch(ProjectSearch search) throws DatabaseException
    {
        try
        {
            PersistenceComponent pc = (PersistenceComponent) serviceManager.lookup(PersistenceComponent.ROLE);
            return pc.getSession().find("SELECT FROM " + Project.class.getName());
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    private ServiceManager serviceManager = null;
    public void service(ServiceManager sm) throws ServiceException
    {
        serviceManager = sm;
    }

}
