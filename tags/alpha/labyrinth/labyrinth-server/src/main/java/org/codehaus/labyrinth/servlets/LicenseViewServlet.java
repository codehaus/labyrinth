/*
 * Created on 9/02/2003
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
 * @author ben
 */
public class LicenseViewServlet extends OverlordVelocityServlet
{
  /* (non-Javadoc)
      * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
      */
  protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) throws Exception
  {
    // TODO Auto-generated method stub
    return getTemplate("velocity/LicenseViewServlet.vm");
  }

}
