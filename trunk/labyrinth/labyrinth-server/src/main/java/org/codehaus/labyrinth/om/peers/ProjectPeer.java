package org.codehaus.labyrinth.om.peers;

import java.util.List;

import org.codehaus.labyrinth.DatabaseException;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface ProjectPeer
{
    static final String ROLE = ProjectPeer.class.getName();   
    List doSearch(ProjectSearch search) throws DatabaseException;
}
