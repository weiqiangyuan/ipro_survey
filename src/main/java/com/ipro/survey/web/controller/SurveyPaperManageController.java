package com.ipro.survey.web.controller;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.service.SurveyPaperManageService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.PaperListVO;
import com.ipro.survey.web.vo.PaperVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by weiqiang.yuan on 2015/6/12 19:03.
 */
@Controller
@RequestMapping("/admin/paper")
public class SurveyPaperManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyPaperManageService surveyPaperManageService;

    @RequestMapping(value = { "/create" })
    @ResponseBody
    public JsonResult createPaper(@RequestBody PaperVO paperVO) {

        try {
            logger.info("input param {}", paperVO);
            surveyPaperManageService.createSurveyPaper(paperVO);
            return JsonResult.successJsonResult(null);
        } catch (PaperManageException e) {
            logger.error("创建试卷发生异常", e);
            return JsonResult.failureJsonResult("创建试卷发生异常");
        } catch (Throwable e) {
            logger.error("创建试卷发生未知异常", e);
            return JsonResult.failureJsonResult("创建试卷发生未知异常");
        }
    }

    @RequestMapping(value = { "/update" })
    @ResponseBody
    public JsonResult editPaper(@RequestBody PaperVO paperVO, HttpServletRequest request) {

        try {
            logger.info("input param {}", paperVO);
            surveyPaperManageService.createSurveyPaper(paperVO);
            return JsonResult.successJsonResult(null);
        } catch (PaperManageException e) {
            logger.error("创建试卷发生异常", e);
            return JsonResult.failureJsonResult("创建试卷发生异常");
        } catch (Throwable e) {
            logger.error("创建试卷发生未知异常", e);
            return JsonResult.failureJsonResult("创建试卷发生未知异常");
        }
    }

    @RequestMapping(value = { "/list" })
    @ResponseBody
    public JsonResult listPaper(String paperName) {

        try {
            logger.info("listPaper param {}", paperName);
            List<PaperListVO> paperList = surveyPaperManageService.getPaperList(paperName);
            return JsonResult.successJsonResult(paperList);
        } catch (PaperManageException e) {
            logger.error("创建试卷发生异常", e);
            return JsonResult.failureJsonResult("创建试卷发生异常");
        } catch (Throwable e) {
            logger.error("创建试卷发生未知异常", e);
            return JsonResult.failureJsonResult("创建试卷发生未知异常");
        }
    }

    @RequestMapping(value = { "/readDetail" })
    @ResponseBody
    public JsonResult readPaperByPaperId(Integer paperId) {

        try {
            logger.info("read paperId {}", paperId);
            PaperVO surveyPaper = surveyPaperManageService.getSurveyPaper(paperId);
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
