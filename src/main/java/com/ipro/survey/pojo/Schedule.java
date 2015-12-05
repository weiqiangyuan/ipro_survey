package com.ipro.survey.pojo;

import com.ipro.survey.persistence.model.Action;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/5 23:45.
 */
public class Schedule {

//    private String name;
    private Integer timePoint;
    private Integer timeLevel;
    private List<Action> action;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Integer getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(Integer timePoint) {
        this.timePoint = timePoint;
    }

    public Integer getTimeLevel() {
        return timeLevel;
    }

    public void setTimeLevel(Integer timeLevel) {
        this.timeLevel = timeLevel;
    }

    public List<Action> getAction() {
        return action;
    }

    public void setAction(List<Action> action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
