package com.ipro.survey.web.controller.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ipro.survey.service.monitor.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.action.ActionService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.action.ActionVO;

/**
 * Created by weiqiang.yuan on 15/12/26 16:50.
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MonitorService monitorService;

    @RequestMapping(value = { "/overallCount" })
    @ResponseBody
    public JsonResult overallCount(String actionName, String actionNo) {

        try {
            logger.info("listAction param {} {}", actionName, actionNo);
            Map map = monitorService.countAllResult();
            return JsonResult.successJsonResult(map);
        } catch (PaperManageException e) {
            logger.error("读取任务列表发生异常", e);
            return JsonResult.failureJsonResult("读取任务列表发生异常");
        } catch (Throwable e) {
            logger.error("读取任务列表发生未知异常", e);
            return JsonResult.failureJsonResult("读取任务列表发生未知异常");
        }
    }


}
