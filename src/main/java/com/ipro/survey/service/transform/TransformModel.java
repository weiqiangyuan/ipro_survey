package com.ipro.survey.service.transform;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.ipro.survey.persistence.model.SurveyQuestion;
import com.ipro.survey.web.vo.Question;

/**
 * Created by weiqiang.yuan on 15/10/10 19:00.
 */
public class TransformModel {

    public static final Function<Question, SurveyQuestion> TO_SURVEY_QUESTION = new Function<Question, SurveyQuestion>() {
        @Override
        public SurveyQuestion apply(Question question) {
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            surveyQuestion.setQuestionTitle(question.getTitle());
            surveyQuestion.setQuestionType(question.getType());
            surveyQuestion.setQuestionContent(JSON.toJSONString(question.getOption()));
            return surveyQuestion;
        }
    };
}
