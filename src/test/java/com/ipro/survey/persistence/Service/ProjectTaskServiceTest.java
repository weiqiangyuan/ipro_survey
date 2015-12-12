package com.ipro.survey.persistence.Service;

import com.ipro.survey.base.BaseServiceTest;
import com.ipro.survey.service.ProjectTaskService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by weiqiang.yuan on 15/12/12 17:22.
 */
public class ProjectTaskServiceTest extends BaseServiceTest {

    @Resource
    private ProjectTaskService projectTaskService;

    @Test
    public void should_create_task() {
        projectTaskService.createUserTaskList("123", "userAccount");
    }
}
