package com.ipro.survey.service.message.impl;

import com.google.common.collect.Maps;
import com.ipro.survey.pojo.NotifyMessage;
import com.ipro.survey.service.message.MessageSender;
import com.ipro.survey.utils.HttpUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/12/19 17:04.
 */
@Service
public class WeChatMesseage implements MessageSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(NotifyMessage notifyMessage) {
        Map param = Maps.newHashMap();
        logger.info("start send wechat msg notifyMessage={}", notifyMessage);
        param.put("msgTitle", notifyMessage.getMsgTitle());
        param.put("msgContent", notifyMessage.getMsgContent());
        param.put("msgDueTime", DateFormatUtils.format(notifyMessage.getMsgDueTime(), "yyyy/MM/dd HH:mm"));
        param.put("remark", notifyMessage.getRemark());
        param.put("redirectUrl", notifyMessage.getRedirectUrl());
        String postUrl = "http://123.56.227.132:3000/template/" + notifyMessage.getUserAccount();
        logger.info("send wechat msg postUrl = {} param={}", postUrl, param);
        HttpUtil.doPost(postUrl, null, param);
    }
}
