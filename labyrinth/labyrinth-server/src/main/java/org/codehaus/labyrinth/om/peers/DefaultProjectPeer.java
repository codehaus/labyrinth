package org.codehaus.labyrinth.om.peers;

import java.util.List;

import net.sf.hibernate.Hibernate;

import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.components.PersistenceComponent;
import org.codehaus.labyrinth.om.Project;
import org.codehaus.labyrinth.om.ProjectBlock;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class DefaultProjectPeer extends BasePeer implements ProjectPeer
{

    /* (non-Javadoc)
     * @see org.codehaus.labyrinth.om.peers.ProjectPeer#doSearch(org.codehaus.labyrinth.om.peers.ProjectSearch)
     */
    public List doSearch(ProjectSearch search) throws DatabaseException
    {
        try
        {
            PersistenceComponent pc = getPersistenceComponent();
            return pc.getSession().find("SELECT FROM " + Project.class.getName());
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }

    /* XXX this is crap hibernate code
     * @see org.codehaus.labyrinth.om.peers.ProjectPeer#getBlocks(org.codehaus.labyrinth.om.Project)
     */
    public List getProjectBlocks(Project project) throws DatabaseException
    {
        try
        {
            PersistenceComponent pc = getPersistenceComponent();
            List l =
                pc.getSession().find(
                    "SELECT FROM " + ProjectBlock.class.getName() + " WHERE rfLProject = ?",
                    project.getId(),
                    Hibernate.INTEGER);

            return l;
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }

}
