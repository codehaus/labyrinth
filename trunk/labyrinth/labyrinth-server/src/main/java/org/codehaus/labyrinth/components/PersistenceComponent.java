package org.codehaus.labyrinth.components;

import net.sf.hibernate.Session;

import org.codehaus.labyrinth.DatabaseException;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface PersistenceComponent {
    public static final String ROLE = PersistenceComponent.class.getName();
    void closeSession() throws DatabaseException;
    void save(Object o) throws DatabaseException;

    void configureORLayer() throws Exception;
    public Object load(Class clazz, Integer id)throws DatabaseException;
    
}
