/*
 * Created on 7/05/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.commands;

import java.io.Serializable;

import org.codehaus.labyrinth.Projects;
import org.codehaus.labyrinth.om.base.BaseProject;
import org.prevayler.Command;
import org.prevayler.PrevalentSystem;

/**
 * @author <a href="bwalding@jakarta.org">Ben Walding</a>
 * @version $Id$
 */
public class AddProject implements Command
{
    private final BaseProject project;
    public AddProject(BaseProject project)
    {
        this.project = project;
    }

    public Serializable execute(PrevalentSystem arg0) throws Exception
    {
        Projects projects = (Projects) arg0;
        projects.addProject(project);
        return this;
    }

}
