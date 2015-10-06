package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author weiqiang.yuan
 * @Time 15/8/7 18:30.
 */
@Repository
public interface SurveyQuestionDao {

    /**
     *
     * @param ids
     * @return
     */
    List<SurveyQuestion> selectByQuestionIds(@Param("ids") List<Integer> ids);

    /**
     * 
     * @param surveyQuestion
     * @return
     */
    int insertQuestion(SurveyQuestion surveyQuestion);

    /**
     *
     * @param surveyQuestion
     * @return
     */
    int updateQuestion(SurveyQuestion surveyQuestion);

}
