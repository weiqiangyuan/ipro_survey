package com.ipro.survey.web.vo.task;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by weiqiang.yuan on 15/12/12 23:39.
 */
public class TaskItemVO implements Serializable {

    private static final long serialVersionUID = -2813907926396152307L;

    private String egTitle;
    private String chTitle;
    private String egDesc;
    private String chDesc;
    private boolean isDone;
    private String url;
    private String type;
    private String icon;
    private String actionNo;
    private String taskNo;
    private Integer scheduleCount;

    public String getEgTitle() {
        return egTitle;
    }

    public void setEgTitle(String egTitle) {
        this.egTitle = egTitle;
    }

    public String getChTitle() {
        return chTitle;
    }

    public void setChTitle(String chTitle) {
        this.chTitle = chTitle;
    }

    public String getEgDesc() {
        return egDesc;
    }

    public void setEgDesc(String egDesc) {
        this.egDesc = egDesc;
    }

    public String getChDesc() {
        return chDesc;
    }

    public void setChDesc(String chDesc) {
        this.chDesc = chDesc;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getActionNo() {
        return actionNo;
    }

    public void setActionNo(String actionNo) {
        this.actionNo = actionNo;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Integer getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(Integer scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
