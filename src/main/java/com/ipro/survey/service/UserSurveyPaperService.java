package com.ipro.survey.service;

import com.google.common.collect.Maps;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.dao.SurveyResultDao;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.persistence.model.SurveyResult;
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
    private ProjectTaskService projectTaskService;
    @Resource
    private ProjectTaskDao projectTaskDao;
    @Resource
    private SurveyResultDao surveyResultDao;

    public UserPaperVO getUserPaperVOByPaperId(Integer paperId, String userAccount, String taskNo) {
        ProjectTask projectTask = projectTaskDao.selectByTaskNo(taskNo);
        String projectNo = projectTask.getProjectUniqNo();
        PaperVO surveyPaper = surveyPaperManageService.getSurveyPaper(paperId);

        UserPaperVO userPaperVO = new UserPaperVO();
        userPaperVO.setQuestionList(surveyPaper.getQuestionList());
        userPaperVO.setDesc(surveyPaper.getDesc());
        userPaperVO.setPaperTitle(surveyPaper.getPaperTitle());
        userPaperVO.setPaperId(surveyPaper.getPaperId());
        userPaperVO.setUserAccount(userAccount);
        userPaperVO.setProjectNo(projectNo);
        userPaperVO.setCreateTime(surveyPaper.getCreateTime());
        userPaperVO.setTaskNo(taskNo);
        userPaperVO.setScheduleCount(projectTask.getScheduleCount());
        return userPaperVO;
    }

    @Transactional
    public Map<String, Object> submitSurvey(UserPaperSubmitParam userPaperSubmitParam) {
        List<Answer> answers = userPaperSubmitParam.getAnswers();
        for (Answer answer : answers) {
            Integer value = answer.getValue();
            if (value == null) {
                continue;
            }
            SurveyResult surveyResult = new SurveyResult();
            surveyResult.setTaskNo(userPaperSubmitParam.getTaskNo());
            surveyResult.setCreateTime(new Date());
            surveyResult.setPaperId(userPaperSubmitParam.getPaperId());
            surveyResult.setQuestionId(answer.getQuestionId());
            surveyResult.setResultValue(answer.getValue());
            surveyResult.setResultOption(String.valueOf(answer.getLocation()));
            surveyResultDao.insertResult(surveyResult);
            logger.info("用户{}提交的结果是{}", userPaperSubmitParam.getUserAccount(), surveyResult);
        }
        Map ret = Maps.newHashMap();
        projectTaskService.userCommitTask(userPaperSubmitParam.getUserAccount(), userPaperSubmitParam.getTaskNo());

        ProjectTask projectTask = projectTaskDao.selectByTaskNo(userPaperSubmitParam.getTaskNo());
        ret.put("scheduleCount", projectTask.getScheduleCount());
        ret.put("projectUniqNo", projectTask.getProjectUniqNo());
        ret.put("userAccount", userPaperSubmitParam.getUserAccount());
        return ret;
    }
}
