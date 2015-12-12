package com.ipro.survey.Enum;

/**
 * Created by weiqiang.yuan on 15/12/5 22:55.
 */
public enum ActionType {

    NOTICE(0, "提醒类型的任务", "alert"), SURVEY(1, "调查问卷的任务", "question");

    public final int code;
    public final String name;
    public final String feName;

    ActionType(int code, String name, String feName) {
        this.code = code;
        this.name = name;
        this.feName = feName;
    }

    public static ActionType codeOf(int v) {
        ActionType[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ActionType s = arr$[i$];
            if (v == s.code) {
                return s;
            }
        }

        return null;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

}
