package com.ipro.survey.persistence.dao.monitor;

import com.ipro.survey.pojo.monitor.ProjectMonitorParam;
import com.ipro.survey.pojo.monitor.ProjectMonitorResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by weiqiang.yuan on 16/5/23 13:26.
 */
public interface ProjectMonitorDao {

    Map<String, ProjectMonitorResult> selectTaskCountByCondition(ProjectMonitorParam param);

}
