package org.codehaus.labyrinth.listener;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.plexus.DefaultPlexusContainer;
import org.apache.plexus.lifecycle.avalon.AvalonServiceManager;
import org.codehaus.labyrinth.components.PersistenceComponent;
import org.codehaus.labyrinth.servlets.LabyrinthServlet;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class AppContextListener implements ServletContextListener
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AppContextListener.class);

    private DefaultPlexusContainer container = null;
    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)
    {
        PropertyConfigurator.configure(getClass().getResource("/log4j.properties"));

        ServletContext context = sce.getServletContext();

        context.log("Initializing Plexus...");
        InputStream is = context.getResourceAsStream("/WEB-INF/plexus.xml");
        if (is == null)
        {
            context.log("Plexus configuration not found");
            throw new RuntimeException("Plexus configuration not found");
        }

        Reader config = new InputStreamReader(is);

        container = new DefaultPlexusContainer();
        LOGGER.info("Creating container");
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
        LabyrinthServlet.setPlexusContainer(context, container);
        LOGGER.info("Plexus Initialized.");
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)
    {
        if (container != null)
        {
            LOGGER.info("Disposing of container");
            ServiceManager sm = (ServiceManager) arg0.getServletContext().getAttribute("plexus.service.manager");
            try
            {
                PersistenceComponent pc = (PersistenceComponent) sm.lookup(PersistenceComponent.ROLE);
                if (pc instanceof Disposable)
                {
                    LOGGER.info("Disposing of PersistenceComponent");
            ((Disposable) pc).dispose();
                }
            }
            catch (ServiceException e)
            {
                e.printStackTrace();
            }

            container.dispose();

        }

    }

}
