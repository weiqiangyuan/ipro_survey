package com.ipro.survey.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by weiqiang.yuan on 15/10/5 13:05.
 */
@Service
public class SurveyQuestionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jdbc.survey.url}")
    private String name;

    public void createSurveyPaper() {
        logger.info("=={}", name);
    }
}
