package com.ipro.survey.web.controller;

import com.ipro.survey.exception.UserException;
import com.ipro.survey.persistence.dao.UserProjectRefDao;
import com.ipro.survey.persistence.model.UserProjectRef;
import com.ipro.survey.pojo.NotifyMessage;
import com.ipro.survey.service.message.mq.NotifyMsgConsumerService;
import com.ipro.survey.service.message.mq.NotifyMsgProducerService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqiang.yuan on 16/2/26 23:34.
 */
@Controller
@RequestMapping("/test/")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NotifyMsgProducerService notifyMsgProducerService;
    @Resource
    private NotifyMsgConsumerService notifyMsgConsumerService;

    @Resource
    private UserProjectRefDao userProjectRefDao;

    @RequestMapping(value = { "/msg" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult createUser() {

        try {
            NotifyMessage notifyMessage = new NotifyMessage();
            notifyMessage.setMsgTitle("这个是消息标题");
            notifyMessage.setRedirectUrl(
                    "http://www.cpzero.cn/schedule?userAccount=userAccount11&projectUniqNo=123$1&scheduleCount=1");
            notifyMessage.setRemark("<p>评论好几下评论好几下评论好几下</p>评论好几下评论好几下评论好几下");
            notifyMessage.setMsgContent("这个是消息内容");
            notifyMessage.setMsgDueTime(new Date());
            notifyMessage.setUserAccount("oewo7wPJosZXZMem-JzRsvGKU7Sk");
            notifyMessage.setNotifyTime(new Date());

            notifyMsgConsumerService.consumeNotifyMsg();
            int i = 0;
            while (i < 100) {
                notifyMsgProducerService.sendNotifyMsg(notifyMessage);
                Thread.sleep(5000);

            }

            return JsonResult.successJsonResult();
        } catch (UserException e) {
            logger.error("创建用户发生异常", e);
            return JsonResult.failureJsonResult("创建用户发生异常");
        } catch (Throwable e) {
            logger.error("创建用户发生未知异常", e);
            return JsonResult.failureJsonResult("创建用户发生未知异常");
        }
    }

    @RequestMapping(value = { "/clearUserProject" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult clear() {

        try {
            int i = userProjectRefDao.deleteByUniqNo();
            logger.info("==={}", i);
            return JsonResult.successJsonResult();
        } catch (UserException e) {
            logger.error("创建用户发生异常", e);
            return JsonResult.failureJsonResult("创建用户发生异常");
        } catch (Throwable e) {
            logger.error("创建用户发生未知异常", e);
            return JsonResult.failureJsonResult("创建用户发生未知异常");
        }
    }


}
