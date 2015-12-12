package com.ipro.survey.Enum;

/**
 * Created by weiqiang.yuan on 15/12/5 22:55.
 */
public enum TaskStatus {

    WAIT_SEND(1, "待推送"), SEND(2, "已推送"), SEND_FAIL(21, "推送失败"), TODO(3, "待完成"), DONE(4, "已完成");

    public final int code;
    public final String name;

    TaskStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TaskStatus codeOf(int v) {
        TaskStatus[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            TaskStatus s = arr$[i$];
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
