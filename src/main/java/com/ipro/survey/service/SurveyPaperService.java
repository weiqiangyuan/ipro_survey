package com.ipro.survey.service;

import com.google.common.collect.Lists;
import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.persistence.dao.SurveyPaperDao;
import com.ipro.survey.persistence.dao.SurveyQuestionDao;
import com.ipro.survey.persistence.model.SurveyPaper;
import com.ipro.survey.persistence.model.SurveyQuestion;
import com.ipro.survey.service.transform.TransformModel;
import com.ipro.survey.utils.StringBasicUtils;
import com.ipro.survey.web.vo.PaperListVO;
import com.ipro.survey.web.vo.Question;
import com.ipro.survey.web.vo.PaperVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
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
    public void createSurveyPaper(PaperVO surveyVO) {
        List<Question> questionList = surveyVO.getQuestionList();
        StringBuilder questionIds = new StringBuilder();

        Integer questionSize = questionList.size();
        for (int i = 0; i < questionSize; i++) {
            Question question = questionList.get(i);
            SurveyQuestion apply = TransformModel.TO_SURVEY_QUESTION.apply(question);
            surveyQuestionDao.insertQuestion(apply);
            questionIds.append(getQuestionId(apply));
            if (i != questionSize - 1) {
                questionIds.append(",");
            }
        }

        SurveyPaper surveyPaper = new SurveyPaper();
        surveyPaper.setPaperName(surveyVO.getPaperTitle());
        surveyPaper.setCreateTime(new Date());
        surveyPaper.setUpdateTime(new Date());
        surveyPaper.setQuestionIds(questionIds.toString());
        surveyPaper.setPaperDesc(surveyVO.getDesc());
        surveyPaperDao.insertPaper(surveyPaper);
    }

    private Integer getQuestionId(SurveyQuestion surveyQuestion) {
        if (surveyQuestion == null && surveyQuestion.getId() == null) {
            throw new PaperManageException("生成问题ID发生异常");
        }
        return surveyQuestion.getId();

    }

    public PaperVO getSurveyPaper(Integer paperId) {
        SurveyPaper surveyPaper = surveyPaperDao.selectByPaperId(paperId);
        String questionIds = surveyPaper.getQuestionIds();
        List<String> idStrings = StringBasicUtils.commaSplitter.splitToList(questionIds);
        List<Integer> ids = Lists.newArrayList();
        for (String idStr : idStrings) {
            ids.add(Integer.valueOf(idStr));
        }
        List<SurveyQuestion> surveyQuestions = surveyQuestionDao.selectByQuestionIds(ids);

        PaperVO paperVO = new PaperVO();

        paperVO.setPaperId(paperId);
        // paperVO.setDesc(surveyPaper.getDesc());
        paperVO.setPaperTitle(surveyPaper.getPaperName());
        paperVO.setCreateTime(surveyPaper.getCreateTime());

        paperVO.setQuestionList(Lists.transform(surveyQuestions, TransformModel.TO_QUESTION_VO));
        return paperVO;
    }

    public List<PaperListVO> getPaperList(String paperName) {
        List<SurveyPaper> surveyPapers = surveyPaperDao.selectByPaperName(paperName);
        return Lists.transform(surveyPapers, TransformModel.TO_PAPERLIST_VO);

    }
}
