package com.ipro.survey.pojo.monitor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 16/5/23 13:54.
 */
public class ProjectMonitorParam {
    private String projectNo;
    private Integer status;

    public String getProjectNo() {
        return projectNo;
    }

    public ProjectMonitorParam setProjectNo(String projectNo) {
        this.projectNo = projectNo;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProjectMonitorParam setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public static ProjectMonitorParam buildParam() {
        return new ProjectMonitorParam();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
