package com.ipro.survey.web.vo.task;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 15/12/12 23:10.
 */
public class UserAllTaskListVO {
    // 唯一标识当前用户参加的是哪一个projectNo，多次参加同一个project，projectUniqNo不同
    private String projectUniqNo;
    // 用户账号
    private String userAccount;
    // 一个需要推送的次数
    private Integer pushCount;
    // 具体的任务列表
    private List<TaskItemVO> listCount;

    public String getProjectUniqNo() {
        return projectUniqNo;
    }

    public void setProjectUniqNo(String projectUniqNo) {
        this.projectUniqNo = projectUniqNo;
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


    public Integer getPushCount() {
        return pushCount;
    }

    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
