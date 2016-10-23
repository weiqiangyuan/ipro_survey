package com.ipro.survey.Enum;

/**
 * Created by weiqiang.yuan on 15/12/5 22:55.
 */
public enum UserType {

    ADMIN(0, "管理员"), WECHAT(1, "微信");

    public final int code;
    public final String name;

    UserType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserType codeOf(int v) {
        UserType[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            UserType s = arr$[i$];
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
