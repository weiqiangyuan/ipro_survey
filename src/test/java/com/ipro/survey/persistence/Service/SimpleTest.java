package com.ipro.survey.persistence.Service;

import com.google.common.collect.Maps;
import com.ipro.survey.utils.HttpUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/12/19 16:23.
 */
public class SimpleTest {
    public static void main(String[] args) {
        String userAccount = "oewo7wAEVuKVjTEEUov3Lc4lI0Uk";
//        String userAccount = "oewo7wMrPRdkfCxLhkQ0qTTMyRME";
        String projectUniqNo = "123$1";
        Map param = Maps.newHashMap();
        param.put("msgTitle", "胡皓哥，在泰国玩的怎么样啊");
        param.put("msgContent", "今天是个重要的日子，你有一些事情需要完成，请点击详情查看。");
        param.put("msgDueTime", DateFormatUtils.format(DateUtils.addHours(new Date(), 12), "yyyy/MM/dd HH:mm"));
        param.put("remark", "看到回复我们一下吧。");
        param.put("redirectUrl", "http://www.cpzero.cn/schedule?userAccount=" + userAccount + "&projectUniqNo="
                + projectUniqNo + "&scheduleCount=1");
        HttpUtil.doPost("http://123.56.227.132:3000/template/" + userAccount, null, param);

    }
}
