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
public class BaseProjectBlock
{
    private Integer id;
    private Integer projectId;
    private Integer blockId;
    /**
     * @return
     */
    public Integer getBlockId()
    {
        return blockId;
    }

    /**
     * @param blockId
     */
    public void setBlockId(Integer blockId)
    {
        this.blockId = blockId;
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
    public Integer getProjectId()
    {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId)
    {
        this.projectId = projectId;
    }

}
