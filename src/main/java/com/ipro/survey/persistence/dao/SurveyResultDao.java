package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.SurveyResult;
import org.springframework.stereotype.Repository;

/**
 * @Author weiqiang.yuan
 * @Time 15/8/7 18:30.
 */
@Repository
public interface SurveyResultDao {

    /**
     * 
     * @param surveyResult
     * @return
     */
    int insertResult(SurveyResult surveyResult);

}
