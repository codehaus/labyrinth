/*
 * Created on Aug 5, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.peers;

import java.util.List;

import net.sf.hibernate.Hibernate;

import org.apache.avalon.framework.service.ServiceException;
import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.components.PersistenceComponent;
import org.codehaus.labyrinth.om.Block;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class DefaultBlockPeer extends BasePeer implements BlockPeer
{
    public Block getByRole(String role) throws DatabaseException
    {
        try
        {
            PersistenceComponent pc = getPersistenceComponent();
            List l =
                pc.getSession().find(
                    "SELECT FROM " + Block.class.getName() + " WHERE txRole = ?",
                    role,
                    Hibernate.STRING);

            if (l.size() == 0)
            {
                return null;
            }
            return (Block) l.get(0);
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
    /**
     * @param block
     */
    public void save(Block block) throws ServiceException, DatabaseException
    {
        getPersistenceComponent().save(block);
    }
}
