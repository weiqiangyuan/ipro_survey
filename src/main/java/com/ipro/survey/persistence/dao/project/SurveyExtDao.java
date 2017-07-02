package com.ipro.survey.persistence.dao.project;

import com.ipro.survey.persistence.model.SurveyExt;
import org.springframework.stereotype.Repository;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface SurveyExtDao {

    int insertExt(SurveyExt surveryExt);
}
