/*
 * Created on Aug 4, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.plexus.DefaultPlexusContainer;
import org.apache.plexus.PlexusContainer;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import com.walding.common.servlet.EnhancedVelocityServlet;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public abstract class LabyrinthServlet extends EnhancedVelocityServlet
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LabyrinthServlet.class);

    private static ThreadLocal contextTL = new ThreadLocal()
    {
        protected synchronized Object initialValue()
        {
            return null;
        }
    };

    public Template handleRequest(HttpServletRequest arg0, HttpServletResponse arg1, Context vcontext) throws Exception
    {
        ServletContext context = arg0.getSession().getServletContext();
        contextTL.set(context);
        Template t = super.handleRequest(arg0, arg1, vcontext);
        vcontext.put("container", LabyrinthServlet.getPlexusContainer(context));
        vcontext.put("componentRepository", LabyrinthServlet.getPlexusContainer(context).getComponentRepository());
        vcontext.put("serviceManager", LabyrinthServlet.getServiceManager(context));
        return t;
    }

    public static ServiceManager getServiceManager(ServletContext context)
    {
        return (ServiceManager) context.getAttribute("plexus.service.manager");
    }

    public ServiceManager getServiceManager()
    {
        return getServiceManager(getServletContext());
    }

    public static PlexusContainer getPlexusContainer(ServletContext context)
    {
        return (PlexusContainer) context.getAttribute("plexus.container");
    }

    public static void setPlexusContainer(ServletContext context, DefaultPlexusContainer container)
    {
        LOGGER.info("c.getDC" + container.getDefaultConfiguration().getAttributeNames().length);
        context.setAttribute("plexus.container", container);
    }

}
