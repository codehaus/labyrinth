package org.codehaus.labyrinth.components;

import net.sf.hibernate.Session;

import org.codehaus.labyrinth.DatabaseException;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface PersistenceComponent
{
    public static final String ROLE = PersistenceComponent.class.getName();


    /**
     * Use of this should be avoided as it exposes Hibernate. 
     * @return
     */
    Session getSession();
    
    void closeSession() throws DatabaseException;

    void configureORLayer() throws Exception;
    public Object load(Class clazz, Integer id) throws DatabaseException;
    void save(Object o) throws DatabaseException;

}
