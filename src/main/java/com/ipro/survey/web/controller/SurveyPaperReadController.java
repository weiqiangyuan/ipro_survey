package com.ipro.survey.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ipro.survey.web.vo.PaperVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.SurveyPaperService;
import com.ipro.survey.web.vo.JsonResult;

/**
 * Created by weiqiang.yuan on 2015/6/12 19:03.
 */
@Controller
@RequestMapping("/info/paper")
public class SurveyPaperReadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyPaperService surveyPaperService;

    @RequestMapping(value = { "/read" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult createPaper(Integer paperId, HttpServletRequest request) {

        try {
            logger.info("read paperId {}", paperId);
            PaperVO surveyPaper = surveyPaperService.getSurveyPaper(paperId);
            return JsonResult.successJsonResult(surveyPaper);
        } catch (PaperManageException e) {
            logger.error("创建试卷发生异常", e);
            return JsonResult.failureJsonResult("创建试卷发生异常");
        } catch (Throwable e) {
            logger.error("创建试卷发生未知异常", e);
            return JsonResult.failureJsonResult("创建试卷发生未知异常");
        }
    }

}
