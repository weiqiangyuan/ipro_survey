package com.ipro.survey.web.vo.task;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/12 23:10.
 */
public class UserTaskListVO {
    // 唯一标识当前用户参加的是哪一个projectNo，多次参加同一个project，projectUniqNo不同
    private String projectUniqNo;
    // 当前是第几次推送
    private Integer scheduleCount;
    // 用户账号
    private String userAccount;

    // 具体的任务列表
    private List<TaskItemVO> listCount;

    public String getProjectUniqNo() {
        return projectUniqNo;
    }

    public void setProjectUniqNo(String projectUniqNo) {
        this.projectUniqNo = projectUniqNo;
    }

    public Integer getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(Integer scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public List<TaskItemVO> getListCount() {
        return listCount;
    }

    public void setListCount(List<TaskItemVO> listCount) {
        this.listCount = listCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
