/*
 * Created on 7/05/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.labyrinth.commands.AddProject;
import org.codehaus.labyrinth.om.Project;
import org.codehaus.labyrinth.om.base.BaseProject;
import org.prevayler.implementation.SnapshotPrevayler;

/**
 * @author <a href="bwalding@jakarta.org">Ben Walding</a>
 * @version $Id$
 */
public class Overlord
{
    public static void main(String args[]) throws Exception
    {

        Overlord o = new Overlord();
        o.doMain(args);
    }

    public void doMain(String args[]) throws Exception
    {

        SnapshotPrevayler prev = startPrevayler();

        Projects projects = (Projects) prevayler.system();
        Project p = new Project();
        p.setProjectId("" + projects.getProjects().size());

        AddProject cmd = new AddProject(p);
        prevayler.executeCommand(cmd);

        Iterator iter = projects.getProjects().values().iterator();
        while (iter.hasNext())
        {
            BaseProject p2 = (BaseProject) iter.next();
            System.out.println("Project: " + p2.getProjectId());
        }

        stopPrevayler();

    }

    private static SnapshotPrevayler prevayler;
    public static SnapshotPrevayler startPrevayler() throws IOException, ClassNotFoundException
    {
        prevayler = new SnapshotPrevayler(new Projects());
        OverlordEngine.instance().setProjects((Projects) prevayler.system());
        return prevayler;
    }

    public static void stopPrevayler() throws IOException
    {
        prevayler.takeSnapshot();
    }
}
