package org.codehaus.labyrinth;

import java.io.Serializable;
/**
 * @author Ben Walding
 *
 */
public class Project implements Serializable
{
    private static final long serialVersionUID = 1234;
    
    private String source;
    private String artifactId;
    private String groupId;
    private String id;
    private String description;
    public Project()
    {
    }

    /**
     * @return
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param string
     */
    public void setId(String string)
    {
        id = string;
    }

    /**
     * @return
     */
    public String getArtifactId()
    {
        return artifactId;
    }

    /**
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return
     */
    public String getGroupId()
    {
        return groupId;
    }

    /**
     * @return
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param string
     */
    public void setArtifactId(String string)
    {
        artifactId = string;
    }

    /**
     * @param string
     */
    public void setDescription(String string)
    {
        description = string;
    }

    /**
     * @param string
     */
    public void setGroupId(String string)
    {
        groupId = string;
    }

    /**
     * @param string
     */
    public void setSource(String string)
    {
        source = string;
    }

}
