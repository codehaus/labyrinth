package org.codehaus.labyrinth.listener;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.codehaus.labyrinth.Overlord;

/**
 * @author Ben Walding
 *
 */
public class AppContextListener implements ServletContextListener
{
   
    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("******************************* HERE");
        try
        {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("WEB-INF/velocity.properties"));
            //p.putAll(EnhancedVelocityServlet.getVelocityProperties());
            Velocity.init(p);
            //RuntimeSingleton.getRuntimeInstance().setProperty("velocity.resource.loader", "file, class");
            //Velocity.init(EnhancedVelocityServlet.getVelocityProperties());
            System.out.println(RuntimeSingleton.getProperty("velocity.resource.loader"));
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! HERE");
            Overlord.startPrevayler();
            
        }

        catch (Exception e)
        {
            e.printStackTrace();
            //LOG.fatal("Couldn't read properties", e);
        }
        try
        {

            //XXX something wrong here, it's a mulch of references
            //      OverlordEngine engine = new OverlordEngine();
            //      sce.getServletContext().setAttribute("OverlordEngine", engine);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)
    {
        try
        {
            Overlord.stopPrevayler();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
