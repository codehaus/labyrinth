/*
 * Created on Aug 5, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.codehaus.labyrinth.om.base;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class BaseProjectBlockProperty
{
    private Integer id;
    private Integer projectBlockId;
    private String name;
    private String value;

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    
    /**
     * @return
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return
     */
    public Integer getProjectBlockId()
    {
        return projectBlockId;
    }

    /**
     * @param projectId
     */
    public void setProjectBlockId(Integer projectBlockId)
    {
        this.projectBlockId = projectBlockId;
    }

}
