/*
 * Created on Aug 4, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import com.walding.common.servlet.EnhancedVelocityServlet;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public abstract class LabyrinthServlet extends EnhancedVelocityServlet
{

    /* (non-Javadoc)
     * @see com.walding.common.servlet.EnhancedVelocityServlet#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.velocity.context.Context)
     */
    public Template handleRequest(HttpServletRequest arg0, HttpServletResponse arg1, Context arg2) throws Exception
    {
        return super.handleRequest(arg0, arg1, arg2);
    }

   
    
}
