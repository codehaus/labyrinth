package org.codehaus.labyrinth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ben Walding
 *
 */
public class OverlordStore
{
  private List projects = new ArrayList();

  public OverlordStore() throws Exception
  {

//    File tempDir = File.createTempFile("asd", "asd");
//    tempDir.delete();
//    tempDir = tempDir.getParentFile();
//
//    //addProjectByURL("file://D:/data/workspace/overlord/project.xml");
//    addProjectByURL("file://D:/data/workspace/montage/project.xml");
//    addProjectByURL("file://D:/data/workspace/dataforge-engine/project.xml");
//    addProjectByURL("file://D:/data/workspace/dataforge-editor/project.xml");
//    addProjectByURL("file://D:/data/workspace/jakarta-turbine-maven/project.xml");
//    addProjectByURL("http://tambora.zenplex.org/cgi-bin/viewcvs.cgi/*checkout*/tambora/project.xml?rev=HEAD&content-type=text/xml");
//    addProjectByURL("http://cvs.werken.com/viewcvs.cgi/*checkout*/werkz/project.xml?rev=HEAD&cvsroot=werkz&content-type=text/xml");
//    addProjectByURL("http://cvs.werken.com/viewcvs.cgi/*checkout*/werkflow/project.xml?rev=HEAD&cvsroot=werkflow&content-type=text/xml");
//    addProjectByURL("http://cvs.werken.com/viewcvs.cgi/*checkout*/jaxen/project.xml?rev=HEAD&cvsroot=jaxen&content-type=text/xml");
//
//    addProjectByURL(
//      "http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/project.xml?rev=HEAD&content-type=text/xml",
//      new File("..", "project.xml"));
//
//    addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/logging/project.xml?rev=HEAD&content-type=text/xml");
//    addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/digester/project.xml?rev=HEAD&content-type=text/xml");
//    addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/betwixt/project.xml?rev=HEAD&content-type=text/xml");
//    addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/beanutils/project.xml?rev=HEAD&content-type=text/xml");
//    addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/collections/project.xml?rev=HEAD&content-type=text/xml");
//    //addProjectByURL("http://cvs.apache.org/viewcvs.cgi/*checkout*/jakarta-commons/sql/project.xml?rev=HEAD&content-type=text/xml");

  }
  
  public void addProject(Project pf) {
      projects.add(pf);
  }
  
//  private void addProjectByURL(String url, File f) throws Exception {
//      OverlordFerret of = new OverlordFerret();
//      projects.add(of.getProjectByURL(url, f));
//  }
//  
//  private void addProjectByURL(String url) throws Exception {
//        OverlordFerret of = new OverlordFerret();
//        projects.add(of.getProjectByURL(url));
//    }

  /**
   * 
   * @return List //of OverlordProject
   */
  public List getProjects()
  {
    return projects;
  }

  public Project getProject(String artifactId)
  {
    Iterator iter = projects.iterator();
    while (iter.hasNext())
    {
      Project facade = (Project) iter.next();
      if (facade.getArtifactId().equals(artifactId))
        return facade;
    }
    return null;
  }

  

}
