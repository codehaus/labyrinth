/*
 * Created on 9/02/2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import com.walding.common.servlet.Toggler;

/**
 * @author ben
 */
public class AdvancedSearchServlet extends OverlordVelocityServlet
{

    /* (non-Javadoc)
     * @see org.apache.velocity.servlet.VelocityServlet#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context)
        throws Exception
    {
        String query = request.getParameter("query");
        context.put("query", query);

        if (query != null && query.trim().length() != 0)
        {

        }
        else
        {
            context.put("projects", Collections.EMPTY_LIST);
        }

        String[] ab = { "a", "b" };
        context.put("rowtoggler", new Toggler(ab, 0));
        return getTemplate("velocity/AdvancedSearchServlet.vm");
    }

}
