/*
 * Created on 8/02/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author Ben Walding
 */
public class ManageProjectsServlet extends OverlordVelocityServlet
{

  /**
   * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
   */
  protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
    throws Exception
  {
    //context.put("engine", getEngine());
    String action = request.getParameter("action");
    if ("add".equals(action))
    {
      String url = request.getParameter("url");
     // Project p = //getEngine().getLibrary().addProject(url);
     // response.sendRedirect("../servlet/projectview?artifactId=" + p.getArtifactId());
    }
    return getTemplate("velocity/ManageProjectsServlet.vm");
  }

}
