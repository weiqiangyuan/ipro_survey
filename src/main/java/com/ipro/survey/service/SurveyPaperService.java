package com.ipro.survey.service;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.persistence.dao.SurveyPaperDao;
import com.ipro.survey.persistence.dao.SurveyQuestionDao;
import com.ipro.survey.persistence.model.SurveyPaper;
import com.ipro.survey.persistence.model.SurveyQuestion;
import com.ipro.survey.service.transform.TransformModel;
import com.ipro.survey.web.vo.Question;
import com.ipro.survey.web.vo.SurveyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/5 13:05.
 */
@Service
public class SurveyPaperService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SurveyQuestionDao surveyQuestionDao;
    @Resource
    private SurveyPaperDao surveyPaperDao;

    @Transactional
    public void createSurveyPaper(SurveyVO surveyVO) {
        try {
            SurveyPaper surveyPaper = new SurveyPaper();
            surveyPaper.setPaperName(surveyPaper.getPaperName());
            surveyPaperDao.insertPaper(surveyPaper);
            List<Question> questionList = surveyVO.getQuestionList();
            for (Question question : questionList) {
                surveyQuestionDao.insertQuestion(TransformModel.TO_SURVEY_QUESTION.apply(question));
            }
        } catch (Exception e) {
            logger.error("创建问卷发生异常{}", surveyVO, e);
            throw new PaperManageException("创建问卷发生异常");
        }
    }

    private void insertSurveyQuestion(SurveyQuestion surveyQuestion) {
        surveyQuestionDao.insertQuestion(surveyQuestion);
    }
}
