package com.ipro.survey.persistence.Service;

import com.ipro.survey.base.BaseServiceTest;
import com.ipro.survey.service.ProjectTaskService;
import com.ipro.survey.web.vo.task.UserTaskListVO;
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
        projectTaskService.createUserTaskList("123$1", "userAccount11");
    }

    @Test
    public void should_get_user_current_list() {
        UserTaskListVO userAccount11 = projectTaskService.getUserTaskList("123$1", "userAccount11", 1);
        logger.info("=={}", userAccount11);
    }
}
