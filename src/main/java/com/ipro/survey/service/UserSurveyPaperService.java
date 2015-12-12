package com.ipro.survey.service;

import com.google.common.collect.Maps;
import com.ipro.survey.persistence.dao.HealthProjectDao;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.dao.SurveyResultDao;
import com.ipro.survey.persistence.model.HealthProject;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.persistence.model.SurveyResult;
import com.ipro.survey.utils.StringBasicUtils;
import com.ipro.survey.web.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 15/10/25 10:31.
 */
@Service
public class UserSurveyPaperService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyPaperManageService surveyPaperManageService;
    @Resource
    private HealthProjectDao healthProjectDao;
    @Resource
    private ProjectTaskDao projectTaskDao;
    @Resource
    private SurveyResultDao surveyResultDao;

    public UserPaperVO getUserPaperVOByPaperId(Integer paperId, String userAccount, String taskNo) {
        ProjectTask projectTask = projectTaskDao.selectByTaskNo(taskNo);
        String projectNo = projectTask.getProjectNo();
        PaperVO surveyPaper = surveyPaperManageService.getSurveyPaper(paperId);

        UserPaperVO userPaperVO = new UserPaperVO();
        userPaperVO.setQuestionList(surveyPaper.getQuestionList());
        userPaperVO.setDesc(surveyPaper.getDesc());
        userPaperVO.setPaperTitle(surveyPaper.getPaperTitle());
        userPaperVO.setPaperId(surveyPaper.getPaperId());
        userPaperVO.setUserAccount(userAccount);
        userPaperVO.setProjectNo(projectNo);
        userPaperVO.setCreateTime(surveyPaper.getCreateTime());
        return userPaperVO;
    }

    @Transactional
    public Map<String, Object> submitSurvey(UserPaperSubmitParam userPaperSubmitParam) {
        List<Answer> answers = userPaperSubmitParam.getAnswers();
        for (Answer answer : answers) {
            SurveyResult surveyResult = new SurveyResult();
            surveyResult.setTaskNo(userPaperSubmitParam.getTaskNo());
            surveyResult.setCreateTime(new Date());
            surveyResult.setPaperId(userPaperSubmitParam.getPaperId());
            surveyResult.setQuestionId(answer.getQuestionId());
            surveyResult.setResultValue(answer.getValue());
            surveyResult.setResultOption(String.valueOf(answer.getLocation()));
            surveyResultDao.insertResult(surveyResult);
        }
        Map ret = Maps.newHashMap();
        ret.put("paperCount", 1);
        ret.put("completeCount", 1);
        ret.put("haveNExtPaper", false);
        ret.put("nextPageUrl", "hehe");
        return ret;
    }
}
