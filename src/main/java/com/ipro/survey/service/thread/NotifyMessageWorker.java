package com.ipro.survey.service.thread;

import com.ipro.survey.service.message.MessageGenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weiqiang.yuan on 16/4/4 23:09.
 */
public class NotifyMessageWorker implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(NotifyMessageWorker.class);
    private MessageGenerateService messageGenerateService;

    private String projectUniqNo;
    private String userAccount;

    public NotifyMessageWorker(String userAccount, String projectUniqNo,
            MessageGenerateService messageGenerateService) {
        this.userAccount = userAccount;
        this.projectUniqNo = projectUniqNo;
        this.messageGenerateService = messageGenerateService;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        logger.info("NotifyMessageWorker projectUniqNo {} userAccount {}", projectUniqNo, userAccount);
        messageGenerateService.generateNotifyMessage(projectUniqNo, userAccount);
        logger.info("send notify msg done! send elapsed= {}", System.currentTimeMillis() - start);
    }
}
