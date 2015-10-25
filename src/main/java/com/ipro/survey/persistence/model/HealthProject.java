package com.ipro.survey.persistence.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by weiqiang.yuan on 15/10/25 11:22.
 */
@Repository
public class HealthProject {
    private Integer id;
    private String projectName;
    private String projectDesc;
    private String surveyCount;
    private String paperIds;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(String surveyCount) {
        this.surveyCount = surveyCount;
    }

    public String getPaperIds() {
        return paperIds;
    }

    public void setPaperIds(String paperIds) {
        this.paperIds = paperIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
