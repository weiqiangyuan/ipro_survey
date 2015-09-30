package com.ipro.survey.web.interceptors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by weiqiang.yuan on 2015/01/15 20:07.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String host = request.getServerName();

        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());

        logger.info("access host: {} requestParam: {}", host, logParams(request));

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
