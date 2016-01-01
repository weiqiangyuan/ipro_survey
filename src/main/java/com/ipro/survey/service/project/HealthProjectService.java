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
import com.ipro.survey.utils.StringBasicUtils;
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

    /**
     * 获取project列表
     * 
     * @param projectName
     * @param projectNo
     * @return
     */
    public List<HealthProjectItemVO> getHealthProjectList(String projectName, String projectNo) {
        List<HealthProject> healthProjects = healthProjectDao.selectHealthProjectList();
        List<HealthProjectItemVO> transformVO = Lists.transform(healthProjects, TransformModel.TO_PROJECT_VO);
        logger.info("healthProjects={} transformVO={}", healthProjects, transformVO);
        return transformVO;
    }

    /**
     * 创建project
     * 
     * @param healthProjectDetailVO
     */
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

    /**
     * 更新project
     * 
     * @param healthProjectDetailVO
     */
    public void updateProject(HealthProjectDetailVO healthProjectDetailVO) {
        checkParam(healthProjectDetailVO);
        HealthProject oldProject = healthProjectDao.selectByProjectNo(healthProjectDetailVO.getProjectNo());
        if (oldProject == null) {
            throw new ProjectException("该project不存在");
        }
        HealthProject healthProject = new HealthProject();
        healthProject.setProjectNo(healthProjectDetailVO.getProjectNo());
        healthProject.setProjectName(healthProjectDetailVO.getProjectName());
        healthProject.setProjectDesc(healthProjectDetailVO.getProjectDesc());
        healthProject.setScheduleTimeLevel(ScheduleTimeLevel.codeOf(healthProjectDetailVO.getScheduleTimeLevel()));
        healthProject.setSchedule(generateScheduleFormular(healthProjectDetailVO.getActionList()));
        logger.info("更新project参数{}", healthProject);
        int ret = healthProjectDao.updateProject(healthProject);
        logger.info("更新project结果{}", ret);
        if (ret <= 0) {
            throw new ProjectException("更新project异常");
        }
    }

    public HealthProjectDetailVO projectDetail(String projectNo) {
        HealthProject oldProject = healthProjectDao.selectByProjectNo(projectNo);
        if (oldProject == null) {
            throw new ProjectException("该project不存在");
        }
        HealthProjectDetailVO healthProjectDetailVO = new HealthProjectDetailVO();
        healthProjectDetailVO.setProjectNo(projectNo);
        healthProjectDetailVO.setProjectDesc(oldProject.getProjectDesc());
        healthProjectDetailVO.setProjectName(oldProject.getProjectName());
        healthProjectDetailVO.setScheduleTimeLevel(oldProject.getScheduleTimeLevel().getCode());

        List<ActionInProject> actionInProjectList = Lists.newArrayList();
        String schedule = oldProject.getSchedule();
        List<String> itemList = StringBasicUtils.lineSplitter.splitToList(schedule);
        for (String item : itemList) {
            List<String> actionTimeGroupList = StringBasicUtils.colonSplitter.splitToList(item);
            String timePoint = actionTimeGroupList.get(0);
            String actionGroup = actionTimeGroupList.get(1);
            List<String> actionList = StringBasicUtils.commaSplitter.splitToList(actionGroup);
            for (String actionItem : actionList) {
                ActionInProject actionInProject = new ActionInProject();
                actionInProject.setActionNo(actionItem);
                actionInProject.setTimePoint(Integer.valueOf(timePoint));
                actionInProjectList.add(actionInProject);
            }
        }
        healthProjectDetailVO.setActionList(actionInProjectList);
        logger.info("读取的project详情");
        return healthProjectDetailVO;
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
