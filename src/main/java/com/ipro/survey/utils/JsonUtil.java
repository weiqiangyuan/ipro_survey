package com.ipro.survey.utils;

import com.google.common.collect.Maps;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by weiqiang.yuan on 2014/12/23 20:50.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseJson(String jsonData, Class<T> classType) {
        try {
            return objectMapper.readValue(jsonData, classType);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            return null;
        }
    }

    public static String toJson(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            return null;
        }
    }

    public static Map<String,Object> jsonToMap(String result){
        Map<String,Object> maps = Maps.newHashMap();
        try {
            maps = objectMapper.readValue(result, Map.class);

        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return maps;
    }

}