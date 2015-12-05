package com.ipro.survey.Enum;

/**
 * Created by weiqiang.yuan on 15/12/5 22:55.
 */
public enum ScheduleTimeLevel {

    YEAR(1, "年"), MONTH(2, "月"), DAY(3, "日"), HOUR(4, "小时"), MINUTE(5, "分钟"), SECOND(6, "秒");

    public final int value;
    public final String name;

    ScheduleTimeLevel(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ScheduleTimeLevel valueOf(int v) {
        ScheduleTimeLevel[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ScheduleTimeLevel s = arr$[i$];
            if (v == s.value) {
                return s;
            }
        }

        return null;
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
