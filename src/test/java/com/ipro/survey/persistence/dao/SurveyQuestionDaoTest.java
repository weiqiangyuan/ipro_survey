package com.ipro.survey.persistence.dao;

import com.ipro.survey.base.BaseServiceTest;
import com.ipro.survey.persistence.model.SurveyQuestion;
import com.ipro.survey.service.SurveyPaperService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/5 09:39.
 */

public class SurveyQuestionDaoTest extends BaseServiceTest {

    @Resource
    private SurveyQuestionDao surveyQuestionDao;

    @Resource
    private SurveyPaperService surveyQuestionService;

    @Test
    public void should_insert_question() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setCreateTime(new Date());
        surveyQuestion.setQuestionContent("dsadsad");
        surveyQuestion.setQuestionTitle("title");
        surveyQuestion.setQuestionType(1);
        surveyQuestionDao.insertQuestion(surveyQuestion);
        logger.info("={}", surveyQuestion.getId());
    }

    @Test
    public void should_select_result() {
        List<SurveyQuestion> surveyQuestions = surveyQuestionDao
                .selectByQuestionIds(Arrays.asList(new Integer[] { 1, 2 }));
        logger.info("=={}", surveyQuestions);
    }

    @Test
    public void should_update_update() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setCreateTime(new Date());
        surveyQuestion.setQuestionContent("hehehehehe");
        surveyQuestion.setQuestionTitle("this is a title");
        surveyQuestion.setQuestionType(2);
        surveyQuestion.setId(2);
        surveyQuestionDao.updateQuestion(surveyQuestion);
    }
}
