package com.ipro.survey.web.vo.project;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/26 16:11.
 */
public class HealthProjectDetailVO {

    private String projectDesc;
    private String projectName;
    private Integer scheduleTimeLevel;
    private List<ActionInProject> actionList;

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScheduleTimeLevel() {
        return scheduleTimeLevel;
    }

    public void setScheduleTimeLevel(Integer scheduleTimeLevel) {
        this.scheduleTimeLevel = scheduleTimeLevel;
    }

    public List<ActionInProject> getActionList() {
        return actionList;
    }

    public void setActionList(List<ActionInProject> actionList) {
        this.actionList = actionList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
