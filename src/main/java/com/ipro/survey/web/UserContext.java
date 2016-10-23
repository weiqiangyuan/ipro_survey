package com.ipro.survey.web;

import com.ipro.survey.web.vo.user.UserTrace;

/**
 * Created by weiqiang.yuan on 16/6/26 16:20.
 */
public class UserContext {

    private static InheritableThreadLocal<UserTrace> USER_TRACE = new InheritableThreadLocal<UserTrace>();

    public static void setUserTrace(UserTrace userTrace) {
        USER_TRACE.set(userTrace);
    }

    public static UserTrace getUserTrace() {
        return USER_TRACE.get();
    }

    public static void releaseAll() {
        USER_TRACE.remove();
    }

}
