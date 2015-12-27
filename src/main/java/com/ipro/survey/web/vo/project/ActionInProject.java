package com.ipro.survey.web.vo.project;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 15/12/26 20:00.
 */
public class ActionInProject {
    private String actionNo;
    private Integer timePoint;

    public String getActionNo() {
        return actionNo;
    }

    public void setActionNo(String actionNo) {
        this.actionNo = actionNo;
    }

    public Integer getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(Integer timePoint) {
        this.timePoint = timePoint;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
