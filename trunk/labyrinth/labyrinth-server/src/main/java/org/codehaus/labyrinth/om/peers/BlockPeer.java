/*
 * Created on Aug 5, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.peers;

import org.apache.avalon.framework.service.ServiceException;
import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.om.Block;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public interface BlockPeer
{
    static final String ROLE = DefaultBlockPeer.class.getName();

    Block getByRole(String role) throws DatabaseException;

    void save(Block block) throws ServiceException, DatabaseException;
}