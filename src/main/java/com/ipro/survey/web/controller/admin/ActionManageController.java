package com.ipro.survey.web.controller.admin;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.action.ActionService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.PaperVO;
import com.ipro.survey.web.vo.action.ActionVO;
import com.ipro.survey.web.vo.project.HealthProjectDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/26 16:50.
 */
@Controller
@RequestMapping("/admin/action")
public class ActionManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ActionService actionService;

    @RequestMapping(value = { "/list" })
    @ResponseBody
    public JsonResult listAction(String actionName, String actionNo) {

        try {
            logger.info("listAction param {} {}", actionName, actionNo);
            List<ActionVO> actionList = actionService.getActionVOList();
            return JsonResult.successJsonResult(actionList);
        } catch (PaperManageException e) {
            logger.error("读取任务列表发生异常", e);
            return JsonResult.failureJsonResult("读取任务列表发生异常");
        } catch (Throwable e) {
            logger.error("读取任务列表发生未知异常", e);
            return JsonResult.failureJsonResult("读取任务列表发生未知异常");
        }
    }


}
