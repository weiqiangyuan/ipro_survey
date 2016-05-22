package com.ipro.survey.service.transform;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.ipro.survey.persistence.model.Action;
import com.ipro.survey.persistence.model.project.HealthProject;
import com.ipro.survey.persistence.model.SurveyPaper;
import com.ipro.survey.persistence.model.SurveyQuestion;
import com.ipro.survey.utils.JsonUtil;
import com.ipro.survey.web.vo.PaperListVO;
import com.ipro.survey.web.vo.Question;
import com.ipro.survey.web.vo.action.ActionVO;
import com.ipro.survey.web.vo.project.HealthProjectItemVO;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.List;

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

    public static final Function<SurveyQuestion, Question> TO_QUESTION_VO = new Function<SurveyQuestion, Question>() {
        @Override
        public Question apply(SurveyQuestion surveyQuestion) {
            Question question = new Question();
            question.setQuestionId(surveyQuestion.getId());
            question.setTitle(surveyQuestion.getQuestionTitle());
            question.setType(surveyQuestion.getQuestionType());
            List optionList = JsonUtil.parseJson(surveyQuestion.getQuestionContent(), List.class);
            question.setOption(optionList);
            return question;
        }
    };

    public static final Function<SurveyPaper, PaperListVO> TO_PAPERLIST_VO = new Function<SurveyPaper, PaperListVO>() {
        @Override
        public PaperListVO apply(SurveyPaper surveyPaper) {
            PaperListVO paperListVO = new PaperListVO();
            paperListVO.setPaperTitle(surveyPaper.getPaperName());
            paperListVO.setPaperId(surveyPaper.getId());
            paperListVO.setDesc(surveyPaper.getPaperDesc());
            paperListVO.setCreateTime(surveyPaper.getCreateTime());
            paperListVO.setUpdateTime(surveyPaper.getUpdateTime());
            return paperListVO;
        }
    };

    public static final Function<HealthProject, HealthProjectItemVO> TO_PROJECT_VO = new Function<HealthProject, HealthProjectItemVO>() {
        @Override
        public HealthProjectItemVO apply(HealthProject healthProject) {
            HealthProjectItemVO healthProjectVO = new HealthProjectItemVO();
            healthProjectVO.setProjectNo(healthProject.getProjectNo());
            healthProjectVO.setProjectName(healthProject.getProjectName());
            healthProjectVO.setCreateTime(DateFormatUtils.format(healthProject.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            healthProjectVO.setUpdateTime(DateFormatUtils.format(healthProject.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            healthProjectVO.setProjectDesc(healthProject.getProjectDesc());
            return healthProjectVO;
        }
    };

    public static final Function<Action, ActionVO> TO_ACTION_VO = new Function<Action, ActionVO>() {
        @Override
        public ActionVO apply(Action action) {
            ActionVO actionVO = new ActionVO();
            actionVO.setActionName(action.getActionName());
            actionVO.setActionNo(action.getActionNo());
            actionVO.setActionType(action.getActionType().getName());
            return actionVO;
        }
    };
}
