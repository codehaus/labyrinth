package org.codehaus.labyrinth.om.base;

/**
 * @author <a href="bwalding@apache.org">Ben Walding</a>
 * @version $Id$
 */
public class BaseBlock
{
    private Integer id;
    private String role;
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
    public String getRole()
    {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role)
    {
        this.role = role;
    }

}
