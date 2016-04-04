package com.ipro.survey.web.controller;

import com.google.common.collect.Maps;
import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.service.ProjectTaskService;
import com.ipro.survey.utils.HttpUtil;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.UserPaperVO;
import com.ipro.survey.web.vo.task.UserAllTaskListVO;
import com.ipro.survey.web.vo.task.UserTaskListVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/12/13 01:08.
 */
@Controller
@RequestMapping("/info/task")
public class UserTaskController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ProjectTaskService projectTaskService;
    @Resource
    private ProjectTaskDao projectTaskDao;

    @RequestMapping(value = { "/createTaskList" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult createTaskList(@RequestParam(required = true) String projectUniqNo,
            @RequestParam(required = true) String userAccount) {

        try {
            logger.info("createTaskList projectUniqNo {} userAccount {}", projectUniqNo, userAccount);
            UserTaskListVO userTaskList = projectTaskService.getUserTaskList(projectUniqNo, userAccount, 1);
            if (CollectionUtils.isEmpty(userTaskList.getListCount())) {
                logger.info("create task");
                projectTaskService.createUserTaskList(projectUniqNo, userAccount);
            } else {
                logger.info("clear task");
                projectTaskDao.resetTask(projectUniqNo, userAccount, 1);
            }
            Map param = Maps.newHashMap();
            param.put("msgTitle", "Schedule of Day 1");
            param.put("msgContent", "今天是个重要的日，你有一些事情需要完成，请点击详情查看。");
            param.put("msgDueTime", DateFormatUtils.format(DateUtils.addHours(new Date(), 12), "yyyy/MM/dd HH:mm"));
            param.put("remark", "");
            param.put("redirectUrl", "http://www.cpzero.cn/schedule?userAccount=" + userAccount + "&projectUniqNo="
                    + projectUniqNo + "&scheduleCount=1");
            HttpUtil.doPost("http://123.56.227.132:3000/template/" + userAccount, null, param);

            return JsonResult.successJsonResult();
        } catch (PaperManageException e) {
            logger.error("创建用户任务发生异常", e);
            return JsonResult.failureJsonResult("创建用户任务发生异常");
        } catch (Throwable e) {
            logger.error("创建用户任务发生未知异常", e);
            return JsonResult.failureJsonResult("创建用户任务发生未知异常");
        }
    }

    @RequestMapping(value = { "/userAllTaskList" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult getUserAllTaskList(@RequestParam(required = true) String projectUniqNo,
            @RequestParam(required = true) String userAccount) {

        try {
            logger.info("userAllTaskList projectUniqNo= {} userAccount= {}", projectUniqNo, userAccount);
            UserAllTaskListVO userTaskList = projectTaskService.getUserAllTaskList(projectUniqNo, userAccount);
            return JsonResult.successJsonResult(userTaskList);
        } catch (PaperManageException e) {
            logger.error("获取用户全部任务列表异常", e);
            return JsonResult.failureJsonResult("获取用户全部任务列表异常");
        } catch (Throwable e) {
            logger.error("获取用户全部任务列表发生未知异常", e);
            return JsonResult.failureJsonResult("获取用户全部任务列表发生未知异常");
        }
    }

    @RequestMapping(value = { "/userCurrentList" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult getUserCurrentList(@RequestParam(required = true) String projectUniqNo,
            @RequestParam(required = true) String userAccount, @RequestParam(required = true) Integer scheduleCount) {

        try {
            logger.info("read projectUniqNo {} userAccount {} scheduleCount {}", projectUniqNo, userAccount,
                    scheduleCount);
            UserTaskListVO userTaskList = projectTaskService.getUserTaskList(projectUniqNo, userAccount, scheduleCount);
            return JsonResult.successJsonResult(userTaskList);
        } catch (PaperManageException e) {
            logger.error("获取问卷发生异常", e);
            return JsonResult.failureJsonResult("获取用户当前任务列表异常");
        } catch (Throwable e) {
            logger.error("获取问卷发生未知异常", e);
            return JsonResult.failureJsonResult("获取用户当前任务发生未知异常");
        }
    }

    @RequestMapping(value = { "/userCommitTask" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult getUserCurrentList(@RequestParam(required = true) String taskNo,
            @RequestParam(required = true) String userAccount) {

        try {
            logger.info("userCommitAction taskNo {} userAccount", taskNo, userAccount);
            projectTaskService.userCommitTask(userAccount, taskNo);
            return JsonResult.successJsonResult();
        } catch (PaperManageException e) {
            logger.error("用户提交任务发生异常", e);
            return JsonResult.failureJsonResult("用户提交任务发生异常");
        } catch (Throwable e) {
            logger.error("用户提交任务发生未知异常", e);
            return JsonResult.failureJsonResult("用户提交任务发生未知异常");
        }
    }

}
