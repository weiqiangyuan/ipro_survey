package com.ipro.survey.web.controller.admin;

import com.ipro.survey.persistence.model.admin.Administrator;
import com.ipro.survey.service.security.SecurityService;
import com.ipro.survey.service.admin.AdministratorService;
import com.ipro.survey.web.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weiqiang.yuan on 16/6/19 15:08.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AdministratorService administratorService;

    @RequestMapping(value = { "/createAdmin" })
    @ResponseBody
    public JsonResult createAdmin(Administrator administrator, HttpServletResponse response) {
        try {
            logger.info("user register ={}", administrator);

            administratorService.insertAdministrator(administrator);
            String s = SecurityService.generateToken(administrator.getAccount(), administrator.getPassword());
            response.setHeader("token", s);
            return JsonResult.successJsonResult("注册成功");
        } catch (Exception e) {
            logger.info("create admin error {}", e);
            return JsonResult.failureJsonResult("注册用户失败");
        }
    }

    @RequestMapping(value = { "/login" })
    @ResponseBody
    public JsonResult login(Administrator administrator, HttpServletResponse response) {
        try {
            logger.info("user login ={}", administrator);
//            administratorService.insertAdministrator(administrator);
            String s = SecurityService.generateToken(administrator.getAccount(), administrator.getPassword());
            response.setHeader("token", s);
            return JsonResult.successJsonResult("注册成功");
        } catch (Exception e) {
            logger.info("create admin error {}", e);
            return JsonResult.failureJsonResult("注册用户失败");
        }
    }

    // @RequestMapping(value = { "/login" })
    // @ResponseBody
    // public JsonResult login(String account,String password) {
    // try {
    // administratorService.insertAdministrator(administrator);
    // return JsonResult.successJsonResult("注册成功");
    // } catch (Exception e) {
    // logger.info("create admin error {}", e);
    // return JsonResult.failureJsonResult(e.getMessage());
    // }
    // }
}
