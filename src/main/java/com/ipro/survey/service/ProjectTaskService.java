package com.ipro.survey.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ipro.survey.Enum.ActionType;
import com.ipro.survey.Enum.ScheduleTimeLevel;
import com.ipro.survey.Enum.TaskStatus;
import com.ipro.survey.exception.TaskException;
import com.ipro.survey.persistence.dao.ActionDao;
import com.ipro.survey.persistence.dao.HealthProjectDao;
import com.ipro.survey.persistence.dao.ProjectTaskDao;
import com.ipro.survey.persistence.dao.UserProjectRefDao;
import com.ipro.survey.persistence.model.Action;
import com.ipro.survey.persistence.model.HealthProject;
import com.ipro.survey.persistence.model.ProjectTask;
import com.ipro.survey.persistence.model.UserProjectRef;
import com.ipro.survey.pojo.Schedule;
import com.ipro.survey.utils.StringBasicUtils;
import com.ipro.survey.utils.UniqueKeyUtil;
import com.ipro.survey.web.vo.task.TaskItemVO;
import com.ipro.survey.web.vo.task.UserTaskListVO;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private UserProjectRefDao userProjectRefDao;

    @Transactional
    public void createUserTaskList(String projectUniqNo, String userAccount) {
        UserProjectRef userProjectRef = userProjectRefDao.selectByProjectUniqNo(projectUniqNo);
        Integer status = userProjectRef.getStatus();
        if (status == 0) {
            logger.info("当前任务无效{}", projectUniqNo);
            return;
        }
        String projectNo = userProjectRef.getProjectNo();
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        // 生成任务计划
        List<Schedule> schedules = generateSchedule(healthProject.getSchedule(), healthProject.getScheduleTimeLevel());
        List<ProjectTask> projectTasks = Lists.newArrayList();
        int scheduleCount = 1;
        for (Schedule schedule : schedules) {
            List<Action> actions = schedule.getAction();
            int index = 1;
            for (Action actionItem : actions) {
                ProjectTask projectTask = new ProjectTask();
                projectTask.setCreateTime(new Date());
                projectTask.setTaskNo(UniqueKeyUtil.generateTaskNo(projectUniqNo, actionItem.getActionNo(), index));
                projectTask.setProjectUniqNo(projectUniqNo);
                projectTask.setUserAccount(userAccount);
                projectTask.setNotifyTime(generateNotifyTime(schedule.getTimeLevel(), schedule.getTimePoint()));
                projectTask.setActionNo(actionItem.getActionNo());
                projectTask.setScheduleCount(scheduleCount);
                projectTask.setStatus(TaskStatus.WAIT_SEND);
                projectTasks.add(projectTask);
                index++;
            }
            scheduleCount++;
        }
        Integer ret = projectTaskDao.insertTask(projectTasks);
        logger.info("插入用户 {} project {} 任务成功，影响条数 {}", userAccount, projectUniqNo, ret);
    }

    /**
     * 获取用户当前推送消息维度的任务列表
     * 
     * @param userAccount
     * @param projectUniqNo
     * @param scheduleCount
     * @return
     */
    public UserTaskListVO getUserTaskList(String projectUniqNo, String userAccount, Integer scheduleCount) {
        List<ProjectTask> projectTasks = projectTaskDao.selectUserCurrentTask(projectUniqNo, userAccount,
                scheduleCount);
        UserTaskListVO userTaskListVO = new UserTaskListVO();
        userTaskListVO.setProjectUniqNo(projectUniqNo);
        userTaskListVO.setUserAccount(userAccount);
        userTaskListVO.setScheduleCount(scheduleCount);

        List<TaskItemVO> taskItemVOList = Lists.newArrayList();
        for (ProjectTask projectTask : projectTasks) {
            taskItemVOList.add(generateTaskItemVO(projectTask));
        }
        userTaskListVO.setListCount(taskItemVOList);
        return userTaskListVO;
    }

    public void userCommitTask(String userAccount, String taskNo) {
        int ret = projectTaskDao.updateProjectTask(taskNo, TaskStatus.DONE.code);
        logger.info("update task {} {} ret {}", userAccount, taskNo, ret);
        if (ret <= 0) {
            throw new TaskException("用户提交task发生异常");
        }
    }

    private TaskItemVO generateTaskItemVO(ProjectTask projectTask) {
        Action action = actionDao.selectByActionNo(projectTask.getActionNo());

        TaskItemVO taskItemVO = new TaskItemVO();
        taskItemVO.setTaskNo(projectTask.getTaskNo());
        taskItemVO.setActionNo(projectTask.getActionNo());
        taskItemVO.setIsDone(projectTask.getStatus() == TaskStatus.DONE ? true : false);
        taskItemVO.setType(action.getActionType().feName);
        taskItemVO.setChDesc(action.getContent());
        taskItemVO.setIcon("");
        if (action.getActionType() == ActionType.SURVEY) {
            taskItemVO.setChDesc(action.getActionDesc());
            String paperId = action.getContent();
            taskItemVO.setUrl("/heartqOl?paperId=" + paperId + "&taskNo=" + projectTask.getTaskNo() + "&userAccount="
                    + projectTask.getUserAccount());
        } else {
            taskItemVO.setChDesc(action.getContent());
        }
        return taskItemVO;
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
