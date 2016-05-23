package com.ipro.survey.persistence.dao.monitor;

import com.ipro.survey.pojo.monitor.ProjectMonitorParam;
import com.ipro.survey.pojo.monitor.ProjectMonitorResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 16/5/23 13:52.
 */
@Repository
public class ProjectMonitorDaoImpl implements ProjectMonitorDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SqlSession sqlSession;

    @Override
    public Map<String, ProjectMonitorResult> selectTaskCountByCondition(ProjectMonitorParam param) {
        Map<String, ProjectMonitorResult> ret = sqlSession
                .selectMap("com.ipro.survey.persistence.dao.monitor.ProjectMonitorDao.selectTaskCountByCondition", param, "userAccount");
        return ret;
    }
}
