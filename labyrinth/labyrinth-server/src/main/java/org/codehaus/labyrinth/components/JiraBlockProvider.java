package org.codehaus.labyrinth.components;

import java.util.List;

import org.codehaus.labyrinth.om.base.BaseProject;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface JiraBlockProvider {
    public static String ROLE = JiraBlockProvider.class.getName();
    
    
    public int getIssueCount(BaseProject project);
    public List getIssues(BaseProject project);
}
