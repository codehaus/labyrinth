package org.codehaus.labyrinth.om.base;

import java.io.Serializable;
/**
 * @author Ben Walding
 *
 */
public abstract class BaseProject implements Serializable
{
    private static final long serialVersionUID = 1234;
    private Integer id;
    private String source;
    private String projectCode;
    private String artifactId;
    private String groupId;
    private String url;
    private String description;
    private String issueXml =
        "http://jira.codehaus.org/secure/IssueNavigator.jspa?pid=10030&resolutionIds=-1&sorter/field=lastupdated&sorter/order=DESC&tempMax=500&view=rss&reset=true";
    /**
     * @return
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url)
    {
        this.url = url;
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
    public String getProjectCode()
    {
        return projectCode;
    }

    /**
     * @param projectId
     */
    public void setProjectCode(String projectId)
    {
        this.projectCode = projectId;
    }

    /**
     * @return
     */
    public String getIssueXml()
    {
        return issueXml;
    }

    public BaseProject()
    {
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
