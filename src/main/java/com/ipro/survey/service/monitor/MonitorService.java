package com.ipro.survey.service.monitor;

import com.google.common.collect.Maps;
import com.ipro.survey.persistence.dao.SurveyPaperDao;
import com.ipro.survey.persistence.dao.UserDao;
import com.ipro.survey.persistence.dao.project.HealthProjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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

    public Map mointorProject(String projectNo) {
        Map retMap = Maps.newHashMap();
        return retMap;

    }
}
