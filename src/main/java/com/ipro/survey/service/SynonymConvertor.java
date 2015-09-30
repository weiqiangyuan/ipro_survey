package com.ipro.survey.service;

import java.util.Set;

/**
 * 同义词转化器
 * Created by weiqiang.yuan on 2015/6/13 13:38.
 */
public interface SynonymConvertor {
    /**
     * 返回originTtext的同义词
     * @param originTtext       原词
     * @return                  同义词
     */
    public Set<String> convert(String originTtext);
}
