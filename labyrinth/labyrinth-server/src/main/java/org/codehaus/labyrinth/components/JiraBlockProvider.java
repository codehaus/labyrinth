package org.codehaus.labyrinth.components;

import java.util.List;

import org.codehaus.labyrinth.om.ProjectBlock;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface JiraBlockProvider {
    public static String ROLE = JiraBlockProvider.class.getName();
    
    
    public int getIssueCount(ProjectBlock project);
    public List getIssues(ProjectBlock project);
}
