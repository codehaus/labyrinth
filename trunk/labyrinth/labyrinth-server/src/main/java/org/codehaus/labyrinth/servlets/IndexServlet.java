package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.PageServlet;

/**
 * @author Ben Walding
 *
 */
public class IndexServlet extends PageServlet
{
  /**
   * @see com.walding.overlord.server.PageServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
   */
  protected Template handleRequest(HttpServletRequest arg0, HttpServletResponse arg1, Context context) throws Exception
  {
    setup(context);
    return getTemplate("velocity/IndexServlet.vm");
  }

}
