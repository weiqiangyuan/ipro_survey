package com.ipro.survey.utils;

import com.google.common.base.Splitter;

/**
 * Created by weiqiang.yuan on 15/10/18 14:12.
 */
public class StringBasicUtils {
    public static final Splitter commaSplitter = Splitter.on(",").omitEmptyStrings().trimResults();
    public static final Splitter lineSplitter = Splitter.on("|").omitEmptyStrings().trimResults();
    public static final Splitter colonSplitter = Splitter.on(":").omitEmptyStrings().trimResults();

}
