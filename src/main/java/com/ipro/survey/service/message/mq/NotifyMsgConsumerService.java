package com.ipro.survey.service.message.mq;

import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipro.survey.service.message.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aliyun.openservices.ons.api.*;
import com.ipro.survey.pojo.NotifyMessage;
import com.ipro.survey.utils.JsonUtil;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 15/12/13 11:25.
 */
@Service
public class NotifyMsgConsumerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageSender weChatMesseage;

    public void consumeNotifyMsg() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "CID_ipro_task_notify_public_consumer");
        properties.put(PropertyKeyConst.AccessKey, "T7ps2O67f0h7rd7P");
        properties.put(PropertyKeyConst.SecretKey, "WnFRexI5l6GN4RhscUqBOkzYTMWAkv");
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("ipro_healthtask_notify_public", "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
                logger.info("Receive: {}", message);
                byte[] body = message.getBody();
                NotifyMessage notifyMessage = JSON.parseObject(body, NotifyMessage.class);
                logger.info("json String from NotifyMsg={}", notifyMessage);
                weChatMesseage.send(notifyMessage);
                return Action.CommitMessage;
            }
        });
        consumer.start();
        logger.info("Consumer Started");
    }
}
