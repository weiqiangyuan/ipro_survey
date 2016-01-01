package com.ipro.survey.persistence.dao.project;

import com.ipro.survey.persistence.model.project.HealthProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface HealthProjectDao {

    HealthProject selectByProjectNo(@Param("projectNo") String projectNo);

    List<HealthProject> selectHealthProjectList();

    int insertProject(HealthProject healthProject);

    int updateProject(HealthProject healthProject);

}
