package com.ipro.survey.web.vo.action;

import com.ipro.survey.Enum.ActionType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by weiqiang.yuan on 15/12/26 17:34.
 */
public class ActionVO {

    private String actionNo;
    private String actionName;
    private String actionType;

    public String getActionNo() {
        return actionNo;
    }

    public void setActionNo(String actionNo) {
        this.actionNo = actionNo;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
