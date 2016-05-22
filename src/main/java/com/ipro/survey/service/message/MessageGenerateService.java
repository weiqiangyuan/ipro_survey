package com.ipro.survey.service.message;

import com.google.common.collect.Sets;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.pojo.NotifyMessage;
import com.ipro.survey.service.message.mq.NotifyMsgProducerService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by weiqiang.yuan on 16/1/1 18:09.
 */
@Service
public class MessageGenerateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ProjectTaskDao projectTaskDao;
    @Resource
    private NotifyMsgProducerService notifyMsgProducerService;

    private String titleTemplate = "Task %s Remainder";
    private String redirectTemplate = "http://www.cpzero.cn/schedule?userAccount=%s&projectUniqNo=%s&scheduleCount=%s";

    public void generateNotifyMessage(String projectUniqNo, String userAccount) {
        List<ProjectTask> projectTasks = projectTaskDao.selectUserAllTask(projectUniqNo, userAccount);
        Set<Integer> scheduleCountSet = Sets.newHashSet();
        for (ProjectTask projectTask : projectTasks) {
            if (!scheduleCountSet.contains(projectTask.getScheduleCount())) {
                NotifyMessage notifyMessage = new NotifyMessage();
                notifyMessage.setUserAccount(userAccount);
                notifyMessage.setNotifyTime(projectTask.getNotifyTime());
                notifyMessage.setMsgTitle(String.format(titleTemplate, projectTask.getScheduleCount()));
                notifyMessage.setMsgDueTime(DateUtils.addHours(projectTask.getNotifyTime(), 2));
                notifyMessage.setMsgContent("Please click the details to do your own tasks");
                notifyMessage.setRemark("");
                notifyMessage.setRedirectUrl(
                        String.format(redirectTemplate, userAccount, projectUniqNo, projectTask.getScheduleCount()));
                scheduleCountSet.add(projectTask.getScheduleCount());
                notifyMsgProducerService.sendNotifyMsg(notifyMessage);
            }
        }
    }
}