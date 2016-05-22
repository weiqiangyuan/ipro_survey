package com.ipro.survey.Enum;

/**
 * Created by weiqiang.yuan on 15/12/5 22:55.
 */
public enum ProjectStatus {

    IN(1, "已参加"), DONE(2, "已完成"), QUIT(0, "已退出");

    public final int code;
    public final String name;

    ProjectStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ProjectStatus codeOf(int v) {
        ProjectStatus[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ProjectStatus s = arr$[i$];
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
