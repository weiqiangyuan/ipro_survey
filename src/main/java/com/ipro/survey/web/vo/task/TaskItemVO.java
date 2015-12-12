package com.ipro.survey.web.vo.task;

import com.ipro.survey.pojo.Schedule;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 15/12/12 23:39.
 */
public class TaskItemVO {
    private String EGTitle;
    private String CHTitle;
    private String EGdesc;
    private String CHdesc;
    private boolean isDone;
    private String url;
    private String type;
    private String icon;
    private String actionNo;
    private String taskNo;

    public String getEGTitle() {
        return EGTitle;
    }

    public void setEGTitle(String EGTitle) {
        this.EGTitle = EGTitle;
    }

    public String getCHTitle() {
        return CHTitle;
    }

    public void setCHTitle(String CHTitle) {
        this.CHTitle = CHTitle;
    }

    public String getEGdesc() {
        return EGdesc;
    }

    public void setEGdesc(String EGdesc) {
        this.EGdesc = EGdesc;
    }

    public String getCHdesc() {
        return CHdesc;
    }

    public void setCHdesc(String CHdesc) {
        this.CHdesc = CHdesc;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
