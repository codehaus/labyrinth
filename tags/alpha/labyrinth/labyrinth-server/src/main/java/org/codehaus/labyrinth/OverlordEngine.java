package org.codehaus.labyrinth;
/**
 * @author Ben Walding
 *
 */
public class OverlordEngine
{
    private static final OverlordEngine instance = new OverlordEngine();
  private OverlordStore store;
  private OverlordLibrary library;
//  private OverlordLicense license;
  public static final String KEY = "OverlordEngine";
  public OverlordEngine()
  {
    try
    {
//      store = new OverlordStore();
//      license = new OverlordLicense(getClass().getResourceAsStream("repository-audit.xml"));
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
 
//  public OverlordStore getStore()
//  {
//    return store;
//  }
//  
//  public OverlordLibrary getLibrary()
//  {
//    return library;
//  }
//  
  

//  public void setLibrary(OverlordLibrary library)
//  {
//    this.library = library;
//  }
  
//  public OverlordLicense getLicense()
//   {
//     return license;
//   }
  
  private Projects projects;

/**
 * @return
 */
public Projects getProjects()
{
    return projects;
}

/**
 * @param projects
 */
public void setProjects(Projects projects)
{
    this.projects = projects;
}

    /**
     * @return
     */
    public static OverlordEngine instance()
    {
        return instance;
    }

}
