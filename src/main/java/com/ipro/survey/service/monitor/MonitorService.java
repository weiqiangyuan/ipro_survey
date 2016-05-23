package com.ipro.survey.service.monitor;

import com.google.common.collect.Maps;
import com.ipro.survey.Enum.TaskStatus;
import com.ipro.survey.persistence.dao.SurveyPaperDao;
import com.ipro.survey.persistence.dao.UserDao;
import com.ipro.survey.persistence.dao.monitor.ProjectMonitorDao;
import com.ipro.survey.persistence.dao.monitor.ProjectMonitorDaoImpl;
import com.ipro.survey.persistence.dao.project.HealthProjectDao;
import com.ipro.survey.persistence.model.User;
import com.ipro.survey.pojo.monitor.ProjectMonitorParam;
import com.ipro.survey.pojo.monitor.ProjectMonitorResult;
import com.ipro.survey.pojo.monitor.UserProjectMonitorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * Created by weiqiang.yuan on 16/5/21 16:13.
 */
@Service
public class MonitorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserDao userDao;

    @Resource
    private HealthProjectDao healthProjectDao;

    @Resource
    private SurveyPaperDao surveyPaperDao;

    @Resource
    private ProjectMonitorDaoImpl projectMonitorDaoImpl;

    /**
     * 统计项目、用户、试卷的数量
     * 
     * @return
     */
    public Map countAllResult() {
        int userCount = userDao.countUserByCondition(null);

        int projectCount = healthProjectDao.countProjectByCondition(null);

        int paperCount = surveyPaperDao.countPaperByCondition(null);

        Map retMap = Maps.newHashMap();

        retMap.put("userCount", userCount);
        retMap.put("projectCount", projectCount);
        retMap.put("paperCount", paperCount);

        logger.info("countAllResult={}", retMap);
        return retMap;
    }

    public Map getUserTaskStatusInProject(String projectNo) {

        Map<String, ProjectMonitorResult> doneTaskStat = projectMonitorDaoImpl.selectTaskCountByCondition(
                ProjectMonitorParam.buildParam().setProjectNo(projectNo).setStatus(TaskStatus.DONE.getCode()));

        Map<String, ProjectMonitorResult> allTaskStat = projectMonitorDaoImpl
                .selectTaskCountByCondition(ProjectMonitorParam.buildParam().setProjectNo(projectNo));

        Map<String, UserProjectMonitorResult> finalResult = Maps.newConcurrentMap();

        Set<Map.Entry<String, ProjectMonitorResult>> entries = allTaskStat.entrySet();
        for (Map.Entry<String, ProjectMonitorResult> entry : entries) {
            try {
                String userAccount = entry.getKey();
                ProjectMonitorResult value = entry.getValue();
                User user = userDao.selectByAccount(userAccount);
                ProjectMonitorResult doneValue = doneTaskStat.get(userAccount);

                double progress = 0;
                if (doneValue != null) {
                    Double doneValueCount = Double.valueOf(doneValue.getCountValue());
                    Double allValueCount = Double.valueOf(value.getCountValue());
                    progress = doneValueCount / allValueCount;
                }
                // value.setProgress(progress);
                // value.setUserName(user.getNickName());

                UserProjectMonitorResult result = new UserProjectMonitorResult();
                result.setUserName(user.getNickName());
                result.setUserAccount(user.getAccount());
                result.setProgress(progress);
                result.setAllTaskNumber(value.getCountValue());
                result.setDoneTaskNumber(doneValue == null ? 0 : doneValue.getCountValue());

                finalResult.put(userAccount, result);
            } catch (Exception e) {
                logger.info("get One User Task Result error={}", entry, e);
            }

        }
        return finalResult;

    }
}
