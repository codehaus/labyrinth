package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author Ben Walding
 *
 */
public class ViewResourceServlet extends OverlordVelocityServlet
{

  /**
   * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
   */
  protected Template handleRequest(HttpServletRequest request, HttpServletResponse arg1, Context context)
    throws Exception
  {
    String artifactId = request.getParameter("artifactId");

//    Project facade = getEngine().getStore().getProject(artifactId);
    String s;
//    FileInputStream fis = new FileInputStream(facade.getMavenProject().getFile());
//    s = StreamUtility.readInputStreamIntoString(fis);
//    s = s.replaceAll("<", "&lt;");
//    s = s.replaceAll(">", "&gt;");
//    XMLUtility.escapeString(s);
//    context.put("project-data", s);
//    context.put("project", facade);
    return getTemplate("velocity/ViewResourceServlet.vm");
  }

}
