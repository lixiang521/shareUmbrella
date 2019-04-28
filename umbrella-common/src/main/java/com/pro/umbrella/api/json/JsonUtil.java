package com.pro.umbrella.api.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pro.umbrella.common.json.JsonMapper;
import com.pro.umbrella.common.json.databind.JacksonDateFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.common.base.Strings;

/**
 * Created by lixiang on 2019/04/27.
 */
public class JsonUtil {

    public static JsonMapper jsonMapper = new JsonMapper(true, false, JacksonDateFormat.PATTERN_YYYYMMDDHHMMSS);

    private JsonUtil() {
    }

    public static ObjectMapper instance() {
        return jsonMapper.getDelegate();
    }

    public static String of(Object o) {
        return jsonMapper.writeValueAsString(o);
    }

    @SuppressWarnings("unchecked")
    public static <T> T of(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        return jsonMapper.readValue(json, tClass);
    }

    public static <T> T of(String json, TypeReference<T> reference) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        return jsonMapper.readerFor(reference).readValue(json);
    }

    public static <T> T of(String json, Class<T> clazz, Module module) throws IOException {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        jsonMapper.registerModule(module);
        return jsonMapper.readerFor(clazz).readValue(json);
    }

    public static <T> List<T> ofList(String json, Class<T> tClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        JavaType javaType = jsonMapper.getTypeFactory().constructParametrizedType(ArrayList.class, ArrayList.class,
                tClass);
        return jsonMapper.readValue(json, javaType);
    }

    public static <K, V> Map<K, V> ofMap(String json, Class<K> keyClass, Class<V> valueClass) {
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }
        MapType mapType = jsonMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);
        return jsonMapper.readValue(json, mapType);
    }

    public static String toJson(Object obj) {
        if (null == obj) {
            return null;
        }
        return jsonMapper.writeValueAsString(obj);
    }
}
