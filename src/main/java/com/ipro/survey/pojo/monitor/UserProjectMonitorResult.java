package com.ipro.survey.pojo.monitor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 16/5/23 13:27.
 */
public class UserProjectMonitorResult {
    private String userAccount;
    private Integer allTaskNumber;
    private Integer doneTaskNumber;
    private String userName;
    private double progress;
    private String participateTime;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getAllTaskNumber() {
        return allTaskNumber;
    }

    public void setAllTaskNumber(Integer allTaskNumber) {
        this.allTaskNumber = allTaskNumber;
    }

    public Integer getDoneTaskNumber() {
        return doneTaskNumber;
    }

    public void setDoneTaskNumber(Integer doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(String participateTime) {
        this.participateTime = participateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
