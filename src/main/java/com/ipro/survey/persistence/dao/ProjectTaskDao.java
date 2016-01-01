package com.ipro.survey.persistence.dao;

import com.ipro.survey.persistence.model.ProjectTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weiqiang.yuan on 15/10/25 11:19.
 */
@Repository
public interface ProjectTaskDao {

    ProjectTask selectByTaskNo(@Param("taskNo") String taskNo);

    int updateProjectTask(@Param("taskNo") String taskNo, @Param("status") Integer status);

    List<ProjectTask> selectUserCurrentTask(@Param("projectUniqNo") String projectUniqNo,
            @Param("userAccount") String userAccount, @Param("scheduleCount") Integer scheduleCount);

    List<ProjectTask> selectUserAllTask(@Param("projectUniqNo") String projectUniqNo,
            @Param("userAccount") String userAccount);

    int insertTask(List<ProjectTask> projectTasks);

    int resetTask(@Param("projectUniqNo") String projectUniqNo, @Param("userAccount") String userAccount,
            @Param("scheduleCount") Integer scheduleCount);

}
