package org.codehaus.labyrinth.components;

import java.io.IOException;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public interface CacheComponent {
    public static final String ROLE = CacheComponent.class.getName();

    public Object load(Object key) throws IOException, ClassNotFoundException;
    public void save(Object key, Object value) throws IOException;
}
