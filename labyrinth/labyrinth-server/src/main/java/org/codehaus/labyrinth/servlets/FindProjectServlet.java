package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.codehaus.labyrinth.om.peers.ProjectPeer;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class FindProjectServlet extends LabyrinthServlet
{

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    protected Template handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1, Context vcontext) throws Exception
    {
        ServiceManager sm = getServiceManager(arg0.getSession().getServletContext());
        ProjectPeer pp = (ProjectPeer) sm.lookup(ProjectPeer.ROLE);
        vcontext.put("projects", pp.doSearch(null));
        return getTemplate("velocity/FindProjectServlet.vm");
    }

}
