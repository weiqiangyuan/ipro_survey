package com.ipro.survey.service.project;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ipro.survey.Enum.ScheduleTimeLevel;
import com.ipro.survey.exception.ProjectException;
import com.ipro.survey.persistence.dao.project.HealthProjectDao;
import com.ipro.survey.persistence.model.project.HealthProject;
import com.ipro.survey.service.transform.TransformModel;
import com.ipro.survey.utils.UniqueKeyUtil;
import com.ipro.survey.web.vo.project.ActionInProject;
import com.ipro.survey.web.vo.project.HealthProjectDetailVO;
import com.ipro.survey.web.vo.project.HealthProjectItemVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by weiqiang.yuan on 15/12/26 16:07.
 */
@Service
public class HealthProjectService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HealthProjectDao healthProjectDao;

    public List<HealthProjectItemVO> getHealthProjectList(String projectName, String projectNo) {
        List<HealthProject> healthProjects = healthProjectDao.selectHealthProjectList();
        List<HealthProjectItemVO> transformVO = Lists.transform(healthProjects, TransformModel.TO_PROJECT_VO);
        logger.info("healthProjects={} transformVO={}", healthProjects, transformVO);
        return transformVO;
    }

    public void createProject(HealthProjectDetailVO healthProjectDetailVO) {
        checkParam(healthProjectDetailVO);
        HealthProject healthProject = new HealthProject();
        healthProject.setProjectName(healthProjectDetailVO.getProjectName());
        healthProject.setProjectNo(UniqueKeyUtil.generateProjectNo(healthProjectDetailVO.getProjectName()));
        healthProject.setCreateTime(new Date());
        healthProject.setProjectDesc(healthProjectDetailVO.getProjectDesc());
        healthProject.setScheduleTimeLevel(ScheduleTimeLevel.codeOf(healthProjectDetailVO.getScheduleTimeLevel()));
        healthProject.setSchedule(generateScheduleFormular(healthProjectDetailVO.getActionList()));
        logger.info("创建project参数{}", healthProject);
        int ret = healthProjectDao.insertProject(healthProject);
        logger.info("创建project结果{}", ret);
        if (ret <= 0) {
            throw new ProjectException("保存project异常");
        }
    }

    private void checkParam(HealthProjectDetailVO healthProjectDetailVO) {
        Preconditions.checkArgument(healthProjectDetailVO != null, "请输入参数");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(healthProjectDetailVO.getActionList()), "请输入事件信息");
        Preconditions.checkArgument(healthProjectDetailVO.getProjectName() != null, "请输入项目名称");
        Preconditions.checkArgument(healthProjectDetailVO.getProjectDesc() != null, "请输入项目描述");
        Preconditions.checkArgument(healthProjectDetailVO.getScheduleTimeLevel() != null, "请输入时间单位");
        Preconditions.checkArgument(ScheduleTimeLevel.codeOf(healthProjectDetailVO.getScheduleTimeLevel()) != null,
                "请输入时间单位不合法！");
    }

    public String generateScheduleFormular(List<ActionInProject> actionInProjectList) {
        StringBuilder stringBuilder = new StringBuilder();
        Multimap<Integer, String> multiMap = ArrayListMultimap.create();
        for (ActionInProject actionInProject : actionInProjectList) {
            multiMap.put(actionInProject.getTimePoint(), actionInProject.getActionNo());
        }
        Map<Integer, Collection<String>> integerCollectionMap = multiMap.asMap();
        Set<Map.Entry<Integer, Collection<String>>> entries = integerCollectionMap.entrySet();
        for (Map.Entry<Integer, Collection<String>> entry : entries) {
            Integer key = entry.getKey();
            Collection<String> value = entry.getValue();
            stringBuilder.append(key + ":");
            int index = 0;
            int size = value.size();
            for (String item : value) {
                if (index != size - 1) {
                    stringBuilder.append(item + ",");
                } else {
                    stringBuilder.append(item + "|");
                }
                index++;
            }
        }
        return stringBuilder.toString();

    }

}
