package com.ipro.survey.web.controller;

import javax.annotation.Resource;

import com.ipro.survey.service.UserSurveyPaperService;
import com.ipro.survey.web.vo.PaperVO;
import com.ipro.survey.web.vo.UserPaperSubmitParam;
import com.ipro.survey.web.vo.UserPaperVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.SurveyPaperManageService;
import com.ipro.survey.web.vo.JsonResult;

import java.util.Map;

/**
 * Created by weiqiang.yuan on 2015/6/12 19:03.
 */
@Controller
@RequestMapping("/info/paper")
public class UserPaperController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserSurveyPaperService userSurveyPaperService;

    @RequestMapping(value = { "/getPaperContent" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult getPaperContent(@RequestParam(required = true) Integer paperId,
            @RequestParam(required = true) String taskNo, @RequestParam(required = false) String userAccount) {

        try {
            logger.info("read paperId {} taskNo {} userAccount {}", paperId, taskNo, userAccount);
            UserPaperVO paperVO = userSurveyPaperService.getUserPaperVOByPaperId(paperId, userAccount, taskNo);
            return JsonResult.successJsonResult(paperVO);
        } catch (PaperManageException e) {
            logger.error("获取问卷发生异常", e);
            return JsonResult.failureJsonResult("获取问卷发生异常");
        } catch (Throwable e) {
            logger.error("获取问卷发生未知异常", e);
            return JsonResult.failureJsonResult("获取问卷发生未知异常");
        }
    }

    @RequestMapping(value = { "/submit" }, method = { RequestMethod.POST })
    @ResponseBody
    public JsonResult submit(@RequestBody(required = false) UserPaperSubmitParam paperSubmitParam) {

        try {
            logger.info("paperSubmitParam {}", paperSubmitParam);
            Map<String, Object> ret = userSurveyPaperService.submitSurvey(paperSubmitParam);
            logger.info("提交实际返回结果{}", ret);
            return JsonResult.successJsonResult(ret);
        } catch (PaperManageException e) {
            logger.error("提交问卷发生异常", e);
            return JsonResult.failureJsonResult("提交问卷发生异常");
        } catch (Throwable e) {
            logger.error("提交问卷发生未知异常", e);
            return JsonResult.failureJsonResult("提交问卷发生未知异常");
        }
    }

}
