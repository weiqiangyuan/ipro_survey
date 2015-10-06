package com.ipro.survey.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiqiang.yuan on 2015/6/12 19:03.
 */
@Controller
@RequestMapping("/test")
public class SurveyPaperManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = { "/index" })
    public ModelAndView initPage() {
        logger.info("it works");
        Map<String, Object> model = new HashMap<String, Object>();
        return new ModelAndView("invoicelist", model);
    }

}
