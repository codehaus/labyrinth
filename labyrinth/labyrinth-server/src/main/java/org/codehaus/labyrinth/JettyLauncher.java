package org.codehaus.labyrinth;

import java.net.URL;
import java.net.URLConnection;

import org.codehaus.labyrinth.listener.AppContextListener;
import org.codehaus.labyrinth.servlets.SearchServlet;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SocketListener;
import org.mortbay.http.handler.ResourceHandler;
import org.mortbay.jetty.servlet.WebApplicationContext;
import org.mortbay.jetty.servlet.WebApplicationHandler;
import org.mortbay.util.MultiException;
import org.mortbay.util.Resource;
import org.mortbay.util.URLResource;


/*
 * Created on 7/05/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author <a href="bwalding@jakarta.org">Ben Walding</a>
 * @version $Id$
 */
public class JettyLauncher
{
    public static void main(String args[]) throws MultiException
    {
        JettyLauncher jl = new JettyLauncher();
        jl.doMain(args);
    }

    public void doMain(String args[]) throws MultiException
    {
        int port = 8181;
        String contextString = "overlord";

        // Create the server
        HttpServer server = new HttpServer();

        // Create a port listener
        SocketListener listener = new SocketListener();
        listener.setPort(port);
        server.addListener(listener);

        // Create a context 
        //HttpContext context = new HttpContext();
        //server.addContext(context);

        WebApplicationContext wac = new WebApplicationContext();
        wac.addEventListener(new AppContextListener());
        wac.setContextPath("/" + contextString + "/*");
        server.addContext(wac);

        // Create a servlet container
        WebApplicationHandler wah = new WebApplicationHandler();
        wac.addHandler(wah);

        wah.addServlet("/servlets/Search", SearchServlet.class.getName());
        //wah.addServlet("/servlets/ManageProjects", ManageProjectsServlet.class.getName());

        // Serve static content from the context
        ResourceHandler rh = new ResourceHandler();
        ClassLoader cl = JettyLauncher.class.getClassLoader();
        //System.out.println("ClassLoader: " + cl);
        String errorPage = "errorpage.vm";
        URL resource = cl.getResource("errorpage.vm");
        System.out.println("Resource: " + resource);
        String resourceText = resource.toString();
        resourceText = resourceText.substring(0, resourceText.length() - errorPage.length());
        System.out.println("ResourceText: " + resourceText);
        
        wac.setResourceBase(resourceText);
        try
        {
            URL url = new URL(resourceText);
            URLConnection conn = url.openConnection();
            Resource r = new Fred(url, conn);
            wac.setBaseResource(r);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        wac.addHandler(rh);

        // Start the http server
        server.start();

        System.out.println("*************************************");
        System.out.println("* Overlord Started               ");
        System.out.println("* http://localhost:" + port + "/" + contextString + "/servlets/Search");
        System.out.println("*************************************");

    }

    static class Fred extends URLResource
    {

        /**
         * @param arg0
         * @param arg1
         */
        public Fred(URL arg0, URLConnection arg1)
        {
            super(arg0, arg1);
            // TODO Auto-generated constructor stub
        }
    }
}
