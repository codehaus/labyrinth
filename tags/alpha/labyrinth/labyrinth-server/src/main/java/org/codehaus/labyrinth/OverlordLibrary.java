package org.codehaus.labyrinth;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ben Walding
 *
 * This class is potentially just a thin wrapper for Lucene
 * It does not attempt to hide the fact that Lucene is used.
 */
public class OverlordLibrary
{

    public static void sortByArtifactId(List projectFacades)
    {
        Comparator c = new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                if (o1 == null)
                    return 1;

                if (o2 == null)
                    return -1;

                Project pf1 = (Project) o1;
                Project pf2 = (Project) o2;
                return pf1.getId().compareTo(pf2.getId());
            }
        };

        Collections.sort(projectFacades, c);
    }
}