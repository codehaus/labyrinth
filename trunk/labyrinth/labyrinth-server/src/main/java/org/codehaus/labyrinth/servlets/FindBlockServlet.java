package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class FindBlockServlet extends LabyrinthServlet
{

    protected Template handleRequestInternal(HttpServletRequest request, HttpServletResponse response, Context context) throws Exception
    {
        return getTemplate("velocity/FindBlockServlet.vm");
    }

}
