/*
 * Created on 7/05/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.labyrinth.om.base.BaseProject;
import org.prevayler.implementation.AbstractPrevalentSystem;

/**
 * @author <a href="bwalding@jakarta.org">Ben Walding</a>
 * @version $Id$
 */
public class Projects extends AbstractPrevalentSystem implements Serializable
{
    private static final long serialVersionUID = 1234;
    
    private static final Projects instance = new Projects();
    private Map projects = new HashMap();

    Projects() {
        
    }
    
    public static Projects instance()
    {
        return instance;
    }

    /**
     * @return
     */
    public Map getProjects()
    {
        return projects;
    }

    /**
     * @param map
     */
    public void setProjects(Map map)
    {
        projects = map;
    }

    public void addProject(BaseProject project)
    {
        projects.put(project, project);
    }
    
    
    public BaseProject getProject(String id) {
        Iterator iter = projects.values().iterator();
        while (iter.hasNext())
        {
            BaseProject project = (BaseProject) iter.next();
            if (id.equalsIgnoreCase(project.getProjectId())) {
                return project;
            }
        }
        return null;
    }

}
