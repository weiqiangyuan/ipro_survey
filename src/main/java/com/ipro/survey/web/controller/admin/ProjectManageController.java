package com.ipro.survey.web.controller.admin;

import com.ipro.survey.exception.PaperManageException;
import com.ipro.survey.exception.ProjectException;
import com.ipro.survey.service.project.HealthProjectService;
import com.ipro.survey.web.vo.JsonResult;
import com.ipro.survey.web.vo.project.HealthProjectDetailVO;
import com.ipro.survey.web.vo.project.HealthProjectItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weiqiang.yuan on 15/12/26 16:23.
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HealthProjectService healthProjectService;

    /**
     * 列出所有的Project
     * 
     * @param projectName
     * @param projectNo
     * @return
     */
    @RequestMapping(value = { "/list" })
    @ResponseBody
    public JsonResult listProject(String projectName, String projectNo) {

        try {
            logger.info("listProject param {} {}", projectName, projectNo);
            List<HealthProjectItemVO> projectList = healthProjectService.getHealthProjectList("", "");
            logger.info("listProject result={}", projectName, projectNo);
            return JsonResult.successJsonResult(projectList);
        } catch (PaperManageException e) {
            logger.error("读取项目列表发生异常", e);
            return JsonResult.failureJsonResult("读取项目列表发生异常");
        } catch (Throwable e) {
            logger.error("读取项目列表发生未知异常", e);
            return JsonResult.failureJsonResult("读取项目列表发生未知异常");
        }
    }

    @RequestMapping(value = { "/create" })
    @ResponseBody
    public JsonResult createProject(@RequestBody HealthProjectDetailVO healthProjectDetailVO) {

        try {
            logger.info("input param {}", healthProjectDetailVO);
            healthProjectService.createProject(healthProjectDetailVO);
            return JsonResult.successJsonResult(null);
        } catch (ProjectException e) {
            logger.error("创建project发生异常", e);
            return JsonResult.failureJsonResult(e.getMessage());
        } catch (Throwable e) {
            logger.error("创建project发生未知异常", e);
            return JsonResult.failureJsonResult("创建project发生未知异常");
        }
    }

    @RequestMapping(value = { "/update" })
    @ResponseBody
    public JsonResult updateProject(@RequestBody HealthProjectDetailVO healthProjectDetailVO) {

        try {
            logger.info("input param {}", healthProjectDetailVO);
            healthProjectService.updateProject(healthProjectDetailVO);
            return JsonResult.successJsonResult(null);
        } catch (ProjectException e) {
            logger.error("创建project发生异常", e);
            return JsonResult.failureJsonResult(e.getMessage());
        } catch (Throwable e) {
            logger.error("创建project发生未知异常", e);
            return JsonResult.failureJsonResult("创建project发生未知异常");
        }
    }

    @RequestMapping(value = { "/projectDetail" })
    @ResponseBody
    public JsonResult projectDetail(String projectNo) {

        try {
            logger.info("input param {}", projectNo);
            HealthProjectDetailVO healthProjectDetailVO = healthProjectService.projectDetail(projectNo);
            JsonResult jsonResult = JsonResult.successJsonResult(healthProjectDetailVO);
            logger.info("返回的project详情为{}", jsonResult);
            return jsonResult;
        } catch (ProjectException e) {
            logger.error("读取project发生异常", e);
            return JsonResult.failureJsonResult(e.getMessage());
        } catch (Throwable e) {
            logger.error("读取project发生未知异常", e);
            return JsonResult.failureJsonResult("读取project发生未知异常");
        }
    }

}
