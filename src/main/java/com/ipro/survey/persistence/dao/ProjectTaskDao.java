package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.ProjectTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ipro.survey.persistence.model.HealthProject;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface ProjectTaskDao {

    ProjectTask selectByTaskNo(@Param("taskNo") String taskNo);

    int insertTask(List<ProjectTask> projectTasks);

}
