package com.ipro.survey.web.controller;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.ProjectTaskService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.UserPaperVO;
import com.ipro.survey.web.vo.task.UserTaskListVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 15/12/13 01:08.
 */
@Controller
@RequestMapping("/info/task")
public class UserTaskController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ProjectTaskService projectTaskService;

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
