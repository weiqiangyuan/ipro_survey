package com.ipro.survey.service.message;

import com.ipro.survey.pojo.NotifyMessage;

/**
 * Created by weiqiang.yuan on 15/12/19 16:58.
 */
public interface MessageSender {
    void send(NotifyMessage notifyMessage);
}
