package com.ipro.survey.web.vo.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 16/2/11 18:42.
 */
public class UserDetailVO {
    private String userAccount;
    private String userNickName;
    private String userLevel;

    private UserProjectDetail userProjectDetail;


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public UserProjectDetail getUserProjectDetail() {
        return userProjectDetail;
    }

    public void setUserProjectDetail(UserProjectDetail userProjectDetail) {
        this.userProjectDetail = userProjectDetail;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
