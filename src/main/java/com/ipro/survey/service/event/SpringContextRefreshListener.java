package com.ipro.survey.service.event;

import javax.annotation.Resource;

import com.ipro.survey.service.message.mq.NotifyMsgConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class SpringContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SpringContextRefreshListener.class);

    private static boolean isStart = false;
    private static boolean isReStarted = false;

    @Resource
    private NotifyMsgConsumerService notifyMsgConsumerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!isStart) {
            onStart();
            logger.info("spring init");
            notifyMsgConsumerService.consumeNotifyMsg();
            logger.info("spring init consumer end");
        }
    }

    private static void onStart() {
        isStart = true;
    }

    private static void onReStarted() {
        isReStarted = true;
    }
}