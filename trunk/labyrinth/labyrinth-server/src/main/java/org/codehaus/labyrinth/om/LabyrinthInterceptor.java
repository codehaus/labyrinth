/*
 * Created on Aug 8, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public final class LabyrinthInterceptor implements Serviceable, Interceptor
{
    public static final String ROLE = LabyrinthInterceptor.class.getName();

    public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4)
        throws CallbackException
    {
        if (arg0 instanceof Serviceable)
        {
            Serviceable s = (Serviceable) arg0;
            try
            {
                s.service(sm);
            }
            catch (ServiceException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean onFlushDirty(
        Object arg0,
        Serializable arg1,
        Object[] arg2,
        Object[] arg3,
        String[] arg4,
        Type[] arg5)
        throws CallbackException
    {
        return false;
    }

    public boolean onSave(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4)
        throws CallbackException
    {
        return false;
    }

    public void onDelete(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4)
        throws CallbackException
    {
    }

    public void preFlush(Iterator arg0) throws CallbackException
    {

    }

    public void postFlush(Iterator arg0) throws CallbackException
    {
    }

    public Boolean isUnsaved(Object arg0)
    {
        return null;
    }

    public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5)
    {
        return null;
    }

    public Object instantiate(Class arg0, Serializable arg1) throws CallbackException
    {
        return null;
    }

    private ServiceManager sm = null;
    public void service(ServiceManager arg0) throws ServiceException
    {
        sm = arg0;
    }

}
