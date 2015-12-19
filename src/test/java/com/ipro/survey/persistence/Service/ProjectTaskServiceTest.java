package com.ipro.survey.persistence.Service;

import com.google.common.collect.Maps;
import com.ipro.survey.base.BaseServiceTest;
import com.ipro.survey.service.ProjectTaskService;
import com.ipro.survey.utils.HttpUtil;
import com.ipro.survey.web.vo.task.UserTaskListVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/12/12 17:22.
 */
public class ProjectTaskServiceTest extends BaseServiceTest {

    @Resource
    private ProjectTaskService projectTaskService;

    @Test
    public void should_create_task() {
        projectTaskService.createUserTaskList("123$1", "userAccount11");
    }

    @Test
    public void should_get_user_current_list() {
        UserTaskListVO userAccount11 = projectTaskService.getUserTaskList("123$1", "userAccount11", 1);
        logger.info("=={}", userAccount11);
    }

    @Test
    public void void_should_push_qs() {
        Map param = Maps.newHashMap();
        param.put("msgTitle", "这个是消息标题");
        param.put("msgContent", "这个是消息内容");
        param.put("msgDueTime", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm"));
        param.put("remark", "评论好几下评论\\n好几下评论好几下评论好几<br>下评论好几\\<br\\>下评论好几下");
        param.put("redirectUrl",
                "http://www.cpzero.cn/schedule?userAccount=userAccount11&projectUniqNo=123$1&scheduleCount=1");
        HttpUtil.doPost("http://123.56.227.132:3000/template/oewo7wMrPRdkfCxLhkQ0qTTMyRME", null, param);

    }

    @Test
    public void void_should_push_to_ywq() {
        Map param = Maps.newHashMap();
        param.put("msgTitle", "这个是消息标题");
        param.put("msgContent", "这个是消息内容");
        param.put("msgDueTime", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH点mm分"));
        param.put("remark", "<p>评论好几下评论好几下评论好几下</p>评论好几下评论好几下评论好几下");
        param.put("redirectUrl",
                "http://www.cpzero.cn/schedule?userAccount=userAccount11&projectUniqNo=123$1&scheduleCount=1");
        HttpUtil.doPost("http://123.56.227.132:3000/template/oewo7wPJosZXZMem-JzRsvGKU7Sk", null, param);

    }

    public static void main(String[] args) {
        Map param = Maps.newHashMap();
        param.put("msgTitle", "这个是消息标题");
        param.put("msgContent", "这个是消息内容");
        param.put("msgDueTime", DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm"));
        param.put("remark", "评论好几下评论\\n好几下评论好几下评论好几<br>下评论好几\\<br\\>下评论好几下");
        param.put("redirectUrl",
                "http://www.cpzero.cn/schedule?userAccount=userAccount11&projectUniqNo=123$1&scheduleCount=1");
        HttpUtil.doPost("http://123.56.227.132:3000/template/oewo7wMrPRdkfCxLhkQ0qTTMyRME", null, param);

    }
}
