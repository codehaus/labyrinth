package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author Ben Walding
 *
 */
public class ProjectViewServlet extends OverlordVelocityServlet
{

    /**
     * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
      throws Exception
    {
        
//      context.put("engine", getEngine());
//      String artifactId = request.getParameter("artifactId");
//      context.put("tab", request.getParameter("tab"));
//      
//      Project facade = getEngine().getStore().getProject(artifactId);
//      context.put("project", facade);
//      String[] ab = { "a", "b" };
//      context.put("rowtoggler", new Toggler(ab, 0));
      return getTemplate("velocity/ProjectViewServlet.vm");
    }

}
