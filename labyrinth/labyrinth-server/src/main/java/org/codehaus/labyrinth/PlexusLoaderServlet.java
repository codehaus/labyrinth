/*
 * Created on Aug 4, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.plexus.DefaultPlexusContainer;
import org.apache.plexus.lifecycle.avalon.AvalonServiceManager;

public class PlexusLoaderServlet extends HttpServlet
{

    public PlexusLoaderServlet()
    {
    }

    public void init() throws ServletException
    {
        super.init();
        log("Initializing Plexus...");
        String configFileName = getInitParameter("plexus-config");
        String applicationRoot = getServletContext().getRealPath("");
        System.getProperties().setProperty("plexus.home", applicationRoot + "/WEB-INF");
        File configuration = new File(applicationRoot, configFileName);
        java.io.Reader config = null;
        try
        {
            config = new FileReader(configuration.getAbsolutePath());
        }
        catch (FileNotFoundException e)
        {
            throw new ServletException("Could not find the Plexus configuration!", e);
        }
        container = new DefaultPlexusContainer();
        try
        {
            container.setConfigurationResource(config);
            container.initialize();
            container.start();
        }
        catch (Exception e)
        {
            throw new ServletException("Could not start Plexus!", e);
        }
        getServletContext().setAttribute(
            "plexus.service.manager",
            new AvalonServiceManager(container.getComponentRepository()));
        log("Plexus Initializied.");
    }

    public void destroy()
    {
        try
        {
            container.dispose();
        }
        catch (Exception e)
        {
            log("Could not shutdown Plexus!", e);
        }
        super.destroy();
    }

    private DefaultPlexusContainer container;
}
