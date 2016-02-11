package com.ipro.survey.web.vo.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 16/2/11 18:48.
 */
public class UserProjectDetail {
    private boolean haveProject = false;
    private String projectUniqNo;
    private String projectName;

    public boolean isHaveProject() {
        return haveProject;
    }

    public void setHaveProject(boolean haveProject) {
        this.haveProject = haveProject;
    }


    public String getProjectUniqNo() {
        return projectUniqNo;
    }

    public void setProjectUniqNo(String projectUniqNo) {
        this.projectUniqNo = projectUniqNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
