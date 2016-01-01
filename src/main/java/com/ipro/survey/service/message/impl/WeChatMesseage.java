package com.ipro.survey.service.message.impl;

import com.google.common.collect.Maps;
import com.ipro.survey.pojo.NotifyMessage;
import com.ipro.survey.service.message.MessageSender;
import com.ipro.survey.utils.HttpUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/12/19 17:04.
 */
public class WeChatMesseage implements MessageSender {
    @Override
    public void send(NotifyMessage notifyMessage) {
        Map param = Maps.newHashMap();
        param.put("msgTitle", notifyMessage.getMsgTitle());
        param.put("msgContent", notifyMessage.getMsgContent());
        param.put("msgDueTime", DateFormatUtils.format(notifyMessage.getMsgDueTime(), "yyyy/MM/dd HH:mm"));
        param.put("remark", notifyMessage.getRemark());
        param.put("redirectUrl", notifyMessage.getRedirectUrl());
        HttpUtil.doPost("http://123.56.227.132:3000/template/" + notifyMessage.getUserAccount(), null, param);
    }
}
