package com.ipro.survey.web.interceptors;

import com.ipro.survey.service.security.SecurityService;
import com.ipro.survey.utils.JsonUtil;
import com.ipro.survey.web.vo.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by weiqiang.yuan on 2015/01/15 20:07.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Resource
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        try {
//            String host = request.getServerName();
//            logger.info("access host: {} requestParam: {}", host, logParams(request));
//
//            String requestURI = request.getRequestURI();
//            if (SecurityService.isInWhiteList(requestURI)) {
//                return true;
//            }
//            String token = request.getHeader("token");
//            logger.info("old token={}", token);
//            Pair<String, String> accountAndPwd = securityService.getAccountAndPwdFromToken(token);
//            String account = accountAndPwd.getLeft();
//            String password = accountAndPwd.getRight();
//            // String account = "1";
//            // String password = "2";
//            String checkRet = securityService.checkUser(token);
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(checkRet)) {
//                logger.info("account={} 鉴权不过", account);
//                response477(response);
//                return false;
//            }
//            logger.info("account={} 有权限, 鉴权通过", account);
//
//            response.setHeader("token", SecurityService.generateToken(account, password));
//            StopWatch stopWatch = new StopWatch(handler.toString());
//            stopWatch.start(handler.toString());
//
//            return true;
//        } catch (Exception e) {
//            logger.error("pre handle error", e);
//            response477(response);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    private void response477(ServletResponse response) {
        JsonResult jsonResult = JsonResult.failureJsonResult("用户未登陆", 477);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JsonUtil.toJson(jsonResult));
        } catch (IOException e) {
            logger.info("return user exception", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private static String logParams(ServletRequest request) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            Enumeration<String> names = req.getParameterNames();
            StringBuilder url = new StringBuilder();
            url.append(req.getRequestURI());
            url.append("?");

            while (names.hasMoreElements()) {
                String name = names.nextElement();

                String[] values = request.getParameterValues(name);

                if (values == null) {
                    url.append(name).append("=").append("").append("&");
                } else {
                    for (String v : values) {
                        url.append(name).append("=").append(StringUtils.defaultIfEmpty(v, "")).append("&");
                    }
                }
            }

            String ret = url.toString();
            if (ret.length() > 100000) {
                ret = ret.substring(0, 100000);
            }
            return ret;
        } catch (Exception e) {
            logger.error("logParamsError", e);
            return "error";
        }
    }

}
