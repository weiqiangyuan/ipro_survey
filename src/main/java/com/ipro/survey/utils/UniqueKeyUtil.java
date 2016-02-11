package com.ipro.survey.utils;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by weiqiang.yuan on 15/12/6 23:11.
 */
public class UniqueKeyUtil {

    private static HashFunction hashFunction = Hashing.murmur3_32();

    public static String generateTaskNo(String projectNo, String actionNo, int index) {
        String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        String hashcode = hashFunction.newHasher().putString(projectNo, Charsets.UTF_8)
                .putString(actionNo, Charsets.UTF_8).hash().toString();
        return time + hashcode + Strings.padStart(String.valueOf(index), 3, '0');
    }

    public static String generateProjectNo(String projectName) {
        String time = DateFormatUtils.format(new Date(), "yyMMddHHmmss");
        String hashcode = hashFunction.newHasher().putString(projectName, Charsets.UTF_8).hash().toString();
        return time + hashcode + (int) (Math.random() * 100);
    }

    public static String generateProjectUniqNo(String projectNo) {
        String time = DateFormatUtils.format(new Date(), "yyMMddHHmmss");
        return projectNo + "$" + time;
    }
}
