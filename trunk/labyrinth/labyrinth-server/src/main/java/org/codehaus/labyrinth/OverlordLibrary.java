package org.codehaus.labyrinth;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.codehaus.labyrinth.om.base.BaseProject;

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

                BaseProject pf1 = (BaseProject) o1;
                BaseProject pf2 = (BaseProject) o2;
                return pf1.getProjectCode().compareTo(pf2.getProjectCode());
            }
        };

        Collections.sort(projectFacades, c);
    }
}