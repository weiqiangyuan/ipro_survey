package com.qunar.fuwu.service;

import com.qunar.fuwu.utils.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by weiqiang.yuan on 2015/6/13 14:28.
 */
public class TestInitSynonymParser extends BaseTest {

    @Resource
    private SynonymConvertor airLineSynonymConvertor;

    @Test
    public void test_init_map() {
        Set<String> a = airLineSynonymConvertor.convert("退款");
        Set<String> b = airLineSynonymConvertor.convert("改签");
        Set<String> c = airLineSynonymConvertor.convert("退票");
        Set<String> d = airLineSynonymConvertor.convert("退改签");
        Set<String> e = airLineSynonymConvertor.convert("啊实打实地方");
        Set<String> f = airLineSynonymConvertor.convert("我要退票");
//        logger.info("{} {} {} {}", a, b, c, d);
    }
//
//    @Test
//    public void
}
