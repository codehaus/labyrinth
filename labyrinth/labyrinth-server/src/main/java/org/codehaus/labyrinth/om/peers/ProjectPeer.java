package org.codehaus.labyrinth.om.peers;

import java.util.List;

import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.om.Project;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface ProjectPeer
{
    static final String ROLE = ProjectPeer.class.getName();   
    List doSearch(ProjectSearch search) throws DatabaseException;
    List getProjectBlocks(Project project) throws DatabaseException;
}
