/*
 * Created on Aug 4, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class ContainerStatusServlet extends LabyrinthServlet
{
    ServletContext context;
    /* (non-Javadoc)
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
    public void init(ServletConfig arg0) throws ServletException
    {
        context = arg0.getServletContext();
        super.init(arg0);
    }

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1, Context arg2)
        throws Exception
    {
        arg2.put("container", context.getAttribute("plexus.container"));
        return getTemplate("velocity/ContainerStatusServlet.vm");
    }

}
