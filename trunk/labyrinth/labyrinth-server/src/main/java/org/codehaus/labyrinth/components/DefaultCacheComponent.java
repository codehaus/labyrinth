package org.codehaus.labyrinth.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class DefaultCacheComponent implements CacheComponent {

    /* (non-Javadoc)
     * @see org.codehaus.labyrinth.components.CacheComponent#load(java.lang.Object)
     */
    public Object load(Object key) throws IOException, ClassNotFoundException {
        File f = new File(resolveCache(key));
        
        if (!f.exists()) {
            return null;
        }
        
        FileInputStream fis = new FileInputStream(f);

        ObjectInputStream out = new ObjectInputStream(fis);
        Object value = out.readObject();

        out.close();
        return value;
    }

    /* (non-Javadoc)
     * @see org.codehaus.labyrinth.components.CacheComponent#save(java.lang.Object, java.lang.Object)
     */
    public void save(Object key, Object value) throws IOException {
        // FIXME Auto-generated method stub
        File f = new File(resolveCache(key));
        f.getParentFile().mkdirs();
        
        FileOutputStream fos = new FileOutputStream(f);

        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(value);
        out.close();
    }

    private String resolveCache(Object key) {
        return "cache/" + key;
    }

}
