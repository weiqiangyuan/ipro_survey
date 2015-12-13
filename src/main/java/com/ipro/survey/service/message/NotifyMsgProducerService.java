package com.ipro.survey.service.message;

import com.ipro.survey.pojo.NotifyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

import java.util.Properties;

/**
 * Created by weiqiang.yuan on 15/12/13 11:25.
 */
@Service
public class NotifyMsgProducerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void sendNotifyMsg(NotifyMessage notifyMessage) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, "PID_ipro_task_notify_public_producer");
        properties.put(PropertyKeyConst.AccessKey, "T7ps2O67f0h7rd7P");
        properties.put(PropertyKeyConst.SecretKey, "WnFRexI5l6GN4RhscUqBOkzYTMWAkv");
        Producer producer = ONSFactory.createProducer(properties);

        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();
        Message msg = new Message(
                // Message Topic
                "ipro_healthtask_notify_public",
                // Message Tag,
                // 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
                "TagA",
                // Message Body
                // 任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
                "".getBytes());

        // 设置代表消息的业务关键属性，请尽可能全局唯一。
        // 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");

        // 发送消息，只要不抛异常就是成功
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);

        // 在应用退出前，销毁Producer对象
        // 注意：如果不销毁也没有问题
        producer.shutdown();
    }
}
