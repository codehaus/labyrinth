package org.codehaus.labyrinth.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.components.PersistenceComponent;
import org.codehaus.labyrinth.om.Project;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class ManageProjectServlet extends LabyrinthServlet
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ManageProjectServlet.class);
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse arg1) throws ServletException, IOException
    {
        try
        {
            Project p = getProject(request);
            if (p == null)
            {
                p = new Project();
            }
            LOGGER.info("saving");
            p.setUrl(request.getParameter("url"));
            p.setProjectCode(request.getParameter("projectCode"));

            ServiceManager sm = LabyrinthServlet.getServiceManager(request.getSession().getServletContext());
            PersistenceComponent pm = (PersistenceComponent) sm.lookup(PersistenceComponent.ROLE);
            pm.save(p);
            arg1.sendRedirect("ViewProjectServlet?id=" + p.getId());
        }
        catch (Exception e)
        {
            throw new ServletException(e);

        }
    }

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1, Context vcontext)
        throws Exception
    {
        Project p = getProject(arg0);

        vcontext.put("project", p);

        return getTemplate("velocity/ManageProjectServlet.vm");
    }

    public static Project getProject(HttpServletRequest arg0) throws DatabaseException, ServiceException
    {
        String idString = arg0.getParameter("id");
        Integer id = null;
        Project p = null;
        if (idString != null && idString.trim().length() != 0)
        {
            id = new Integer(idString);
            ServiceManager sm = LabyrinthServlet.getServiceManager(arg0.getSession().getServletContext());
            PersistenceComponent pm = (PersistenceComponent) sm.lookup(PersistenceComponent.ROLE);
            p = (Project) pm.load(Project.class, id);
        }
        return p;
    }

}
