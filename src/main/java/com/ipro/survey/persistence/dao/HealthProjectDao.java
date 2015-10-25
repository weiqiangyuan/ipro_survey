package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.HealthProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface HealthProjectDao {

    HealthProject selectByProjectId(@Param("id") Integer id);
}
