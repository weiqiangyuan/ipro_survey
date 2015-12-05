package com.ipro.survey.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ipro.survey.Enum.ScheduleTimeLevel;
import com.ipro.survey.persistence.dao.ActionDao;
import com.ipro.survey.persistence.dao.HealthProjectDao;
import com.ipro.survey.persistence.model.Action;
import com.ipro.survey.persistence.model.HealthProject;
import com.ipro.survey.pojo.Schedule;
import com.ipro.survey.utils.StringBasicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public void createUserTaskList(String projectNo) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        List<Schedule> schedules = generateSchedue(healthProject.getSchedule(), healthProject.getScheduleTimeLevel());



    }

    public void getUserSchedule(String projectNo) {
        HealthProject healthProject = healthProjectDao.selectByProjectNo(projectNo);
        List<Schedule> schedules = generateSchedue(healthProject.getSchedule(), healthProject.getScheduleTimeLevel());


    }

    private List<Schedule> generateSchedue(String scheduleString, Integer timeLevel) {
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
                Action action = actionDao.selectByActioNo(actionNo);
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

}
