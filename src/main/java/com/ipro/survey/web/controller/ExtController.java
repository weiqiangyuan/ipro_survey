package com.ipro.survey.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import com.ipro.survey.persistence.dao.project.SurveyExtDao;
import com.ipro.survey.persistence.model.SurveyExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipro.survey.exception.ProjectException;
import com.ipro.survey.exception.UserException;
import com.ipro.survey.service.UserService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.user.UserDetailVO;
import com.ipro.survey.web.vo.user.UserVO;

/**
 * Created by weiqiang.yuan on 15/12/5 15:37.
 */
@Controller
@RequestMapping("/ext/")
public class ExtController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyExtDao surveyExtDao;

    @RequestMapping(value = { "/insert" }, method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public JsonResult insertExt(SurveyExt surveyExt) {

        try {
            surveyExtDao.insertExt(surveyExt);
            return JsonResult.successJsonResult();
        } catch (DuplicateKeyException e) {
            logger.error("插入扩展信息发生异常", e);
            return JsonResult.successJsonResult();
        } catch (Throwable e) {
            logger.error("插入扩展信息发生未知异常", e);
            return JsonResult.failureJsonResult("插入扩展信息发生未知异常");
        }
    }

}
