package org.codehaus.labyrinth;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.plexus.DefaultPlexusContainer;
import org.apache.plexus.PlexusContainer;
import org.apache.plexus.lifecycle.avalon.AvalonServiceManager;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class AppContextListener implements ServletContextListener
{
    private PlexusContainer container = null;
    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext context = sce.getServletContext();

        context.log("Initializing Plexus...");
        InputStream is = AppContextListener.class.getResourceAsStream("plexus-config.xml");
        Reader config = new InputStreamReader(is);

        container = new DefaultPlexusContainer();
        try
        {
            container.setConfigurationResource(config);
            container.initialize();
            container.start();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Could not start Plexus!", e);
        }
        context.setAttribute("plexus.service.manager", new AvalonServiceManager(container.getComponentRepository()));
        context.log("Plexus Initializied.");
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)
    {
        container.dispose();
    }

}
