package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.om.Project;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class ViewProjectServlet extends LabyrinthServlet
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ViewProjectServlet.class);

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1, Context vcontext)
        throws Exception
    {
        Project p = ManageProjectServlet.getProject(arg0);

        String tab = arg0.getParameter("tab");
        if (tab == null)
        {
            tab = "overview";
        }

        vcontext.put("tab", tab);
        vcontext.put("project", p);
        return getTemplate("velocity/ViewProjectServlet.vm");
    }

}
