package com.ipro.survey.web.controller;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.SurveyPaperService;
import com.ipro.survey.utils.JsonUtil;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.SurveyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 2015/6/12 19:03.
 */
@Controller
@RequestMapping("/test")
public class SurveyPaperManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyPaperService surveyPaperService;

    @RequestMapping(value = { "/index" })
    public JsonResult createPaper(String inputJson) {

        try {
            logger.info("input param {}", inputJson);
            SurveyVO surveyVO = JsonUtil.parseJson(inputJson, SurveyVO.class);
            surveyPaperService.createSurveyPaper(surveyVO);
            return JsonResult.successJsonResult(null);
        } catch (PaperManageException e) {
            logger.error("创建试卷发生异常", e);
            return JsonResult.failureJsonResult("创建试卷发生异常");
        } catch (Throwable e) {
            logger.error("创建试卷发生未知异常", e);
            return JsonResult.failureJsonResult("创建试卷发生未知异常");
        }

    }

}
