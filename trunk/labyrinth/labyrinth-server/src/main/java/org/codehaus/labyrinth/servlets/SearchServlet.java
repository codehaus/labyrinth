package org.codehaus.labyrinth.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.OverlordEngine;
import org.codehaus.labyrinth.OverlordLibrary;

import com.walding.common.servlet.Toggler;

/**
 * @author Ben Walding
 *
 */
public class SearchServlet extends OverlordVelocityServlet
{

    /**
     * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
        throws Exception
    {

        String query = request.getParameter("query");
        context.put("query", query);
        if (query != null && query.trim().length() != 0)
        {
            List projects = new ArrayList();
            projects.addAll(OverlordEngine.instance().getProjects().getProjects().values());

            OverlordLibrary.sortByArtifactId(projects);
            context.put("projects", projects);

        }
        else
        {
            List projects = new ArrayList();
            projects.addAll(OverlordEngine.instance().getProjects().getProjects().values());
            OverlordLibrary.sortByArtifactId(projects);
            context.put("projects", projects);
        }
        String[] ab = { "a", "b" };
        context.put("rowtoggler", new Toggler(ab, 0));
        return getTemplate("velocity/SearchServlet.vm");
    }

}
