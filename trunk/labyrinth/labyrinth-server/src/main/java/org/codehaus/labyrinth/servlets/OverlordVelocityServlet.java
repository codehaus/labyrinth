/*
 * Created on 8/02/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.velocity.servlet.VelocityServlet;

/**
 * @author ben
 */
public class OverlordVelocityServlet extends VelocityServlet
{
  //private OverlordEngine engine;
  /* (non-Javadoc)
   * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
   */
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
    //engine = (OverlordEngine) config.getServletContext().getAttribute(OverlordEngine.KEY);
  }
  
//  protected OverlordEngine getEngine() {
//      return engine;
//  }

  

}
