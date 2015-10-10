package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.SurveyPaper;
import com.ipro.survey.persistence.model.SurveyQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/5 16:58.
 */
@Repository
public interface SurveyPaperDao {
    /**
     *
     * @param ids
     * @return
     */
    List<SurveyPaper> selectByPaperIds(@Param("ids") List<Integer> ids);

    /**
     *
     * @param paperName
     * @return
     */
    List<SurveyPaper> selectByPaperName(@Param("paperName") String paperName);

    /**
     *
     * @param surveyPaper
     * @return
     */
    int insertPaper(SurveyPaper surveyPaper);

    /**
     *
     * @param surveyPaper
     * @return
     */
    int updatePaper(SurveyPaper surveyPaper);
}
