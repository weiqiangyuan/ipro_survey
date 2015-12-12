package com.ipro.survey.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ipro.survey.Enum.ScheduleTimeLevel;
import com.ipro.survey.Enum.TaskStatus;
import com.ipro.survey.exception.TaskException;
import com.ipro.survey.persistence.dao.ActionDao;
import com.ipro.survey.persistence.dao.HealthProjectDao;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.model.Action;
import com.ipro.survey.persistence.model.HealthProject;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.pojo.Schedule;
import com.ipro.survey.utils.StringBasicUtils;
import com.ipro.survey.utils.UniqueKeyUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/5 22:49.
 */
@Service
public class ProjectTaskService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HealthProjectDao healthProjectDao;
    @Resource
    private ActionDao actionDao;
    @Resource
    private ProjectTaskDao projectTaskDao;

    public void createUserTaskList(String projectNo, String userAccount) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        List<Schedule> schedules = generateSchedule(healthProject.getSchedule(), healthProject.getScheduleTimeLevel());
        List<ProjectTask> projectTasks = Lists.newArrayList();
        for (Schedule schedule : schedules) {
            List<Action> actions = schedule.getAction();
            int index = 0;
            for (Action actionItem : actions) {
                ProjectTask projectTask = new ProjectTask();
                projectTask.setCreateTime(new Date());
                projectTask.setTaskNo(UniqueKeyUtil.generateTaskNo(projectNo, actionItem.getActionNo(), index));
                projectTask.setTaskNo(projectNo);
                projectTask.setUserAccount(userAccount);
                projectTask.setNotifyTime(generateNotifyTime(schedule.getTimeLevel(), schedule.getTimePoint()));
                projectTask.setActionNo(actionItem.getActionNo());
                projectTask.setStatus(TaskStatus.WAIT_SEND);
                projectTasks.add(projectTask);
                index++;
            }
        }
        projectTaskDao.insertTask(projectTasks);
    }

    public void getUserSchedule(String projectNo) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        List<Schedule> schedules = generateSchedule(healthProject.getSchedule(), healthProject.getScheduleTimeLevel());

    }

    private List<Schedule> generateSchedule(String scheduleString, ScheduleTimeLevel timeLevel) {
        List<Schedule> resultSchedule = Lists.newArrayList();
        for (String s : StringBasicUtils.lineSplitter.splitToList(scheduleString)) {
            Schedule schedule = new Schedule();
            List<String> timeAndDetail = StringBasicUtils.colonSplitter.splitToList(s);
            Preconditions.checkNotNull(timeAndDetail);
            Preconditions.checkArgument(timeAndDetail.size() == 2);
            String time = timeAndDetail.get(0);
            String detail = timeAndDetail.get(1);
            List<String> detailActionNo = StringBasicUtils.commaSplitter.splitToList(detail);

            List<Action> actionList = Lists.newArrayList();
            for (String actionNo : detailActionNo) {
                Action action = actionDao.selectByActionNo(actionNo);
                Preconditions.checkNotNull(action);
                actionList.add(action);
            }
            schedule.setTimePoint(Integer.parseInt(time));
            schedule.setTimeLevel(timeLevel);
            schedule.setAction(actionList);
            resultSchedule.add(schedule);
        }
        return resultSchedule;

    }

    private Date generateNotifyTime(ScheduleTimeLevel timeLevel, int timePoint) {
        Preconditions.checkNotNull(timeLevel);
        if (timeLevel.equals(ScheduleTimeLevel.DAY)) {
            return DateUtils.addDays(new Date(), timePoint);
        }
        throw new TaskException("不支持的时间单位");
    }

}
