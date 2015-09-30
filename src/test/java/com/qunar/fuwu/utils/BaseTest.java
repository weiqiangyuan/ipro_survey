package com.qunar.fuwu.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by weiqiang.yuan on 2015/1/15 18:26.
 */
@ContextConfiguration(locations = {"/root-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void init() {
        logger.info("it works");
    }

}
