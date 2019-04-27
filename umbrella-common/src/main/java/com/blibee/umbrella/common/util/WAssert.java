package com.blibee.umbrella.common.util;
/*
 * Copyright (c) 2017 wormpex.com. All Rights Reserved.
 */

import com.blibee.umbrella.common.exception.StatusCodeException;

import com.blibee.umbrella.api.pojo.Status;
import com.blibee.umbrella.api.pojo.WMessage;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by lixiang on 2019/04/27.
 */

public class WAssert {

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new StatusCodeException(Status.error(""));
        }
    }

    public static void isTrue(boolean expression, Status status) {
        if (!expression) {
            throw new StatusCodeException(status);
        }
    }

    public static void isTrue(boolean expression, WMessage msg) {
        if (!expression) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void isTrue(boolean expression, String errorMsg) {
        if (!expression) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void isTrue(boolean expression, String template, Object... params) {
        if (!expression) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    public static <T> T notNull(T object) {
        if (object == null) {
            throw new StatusCodeException(Status.error(""));
        } else {
            return object;
        }
    }

    public static <T> T notNull(T object, Status status) {
        if (object == null) {
            throw new StatusCodeException(status);
        } else {
            return object;
        }
    }

    public static <T> T notNull(T object, WMessage msg) {
        if (object == null) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        } else {
            return object;
        }
    }

    public static <T> T notNull(T object, String errorMsg) {
        if (object == null) {
            throw new StatusCodeException(Status.error(errorMsg));
        } else {
            return object;
        }
    }

    public static <T> T notNull(T object, String template, Object... params) {
        if (object == null) {
            throw new StatusCodeException(Status.error(format(template, params)));
        } else {
            return object;
        }
    }

    public static void hasLength(String text) {
        if (!StringUtils.hasLength(text)) {
            throw new StatusCodeException(Status.error(""));
        }
    }

    public static void hasLength(String text, Status status) {
        if (!StringUtils.hasLength(text)) {
            throw new StatusCodeException(status);
        }
    }

    public static void hasLength(String text, WMessage msg) {
        if (!StringUtils.hasLength(text)) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void hasLength(String text, String errorMsg) {
        if (!StringUtils.hasLength(text)) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void hasLength(String text, String template, Object... params) {
        if (!StringUtils.hasLength(text)) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    public static void notEmpty(Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            throw new StatusCodeException(Status.error(""));
        }
    }

    public static void notEmpty(Object[] array, Status status) {
        if (ObjectUtils.isEmpty(array)) {
            throw new StatusCodeException(status);
        }
    }

    public static void notEmpty(Object[] array, WMessage msg) {
        if (ObjectUtils.isEmpty(array)) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void notEmpty(Object[] array, String errorMsg) {
        if (ObjectUtils.isEmpty(array)) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void notEmpty(Object[] array, String template, Object... params) {
        if (ObjectUtils.isEmpty(array)) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    public static void notEmpty(Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StatusCodeException(Status.error(""));
        }
    }

    public static void notEmpty(Collection collection, Status status) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StatusCodeException(status);
        }
    }

    public static void notEmpty(Collection collection, WMessage msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void notEmpty(Collection collection, String errorMsg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void notEmpty(Collection collection, String template, Object... params) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    public static void notEmpty(Map map) {
        if (CollectionUtils.isEmpty(map)) {
            throw new StatusCodeException(Status.error(""));
        }
    }

    public static void notEmpty(Map map, Status status) {
        if (CollectionUtils.isEmpty(map)) {
            throw new StatusCodeException(status);
        }
    }

    public static void notEmpty(Map map, WMessage msg) {
        if (CollectionUtils.isEmpty(map)) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void notEmpty(Map map, String errorMsg) {
        if (CollectionUtils.isEmpty(map)) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void notEmpty(Map map, String template, Object... params) {
        if (CollectionUtils.isEmpty(map)) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    public static void notEmpty(String str, String errorMsg) {
        if (StringUtils.isEmpty(str)) {
            throw new StatusCodeException(Status.error(errorMsg));
        }
    }

    public static void notEmpty(String str, Status status) {
        if (StringUtils.isEmpty(str)) {
            throw new StatusCodeException(status);
        }
    }

    public static void notEmpty(String str, WMessage msg) {
        if (StringUtils.isEmpty(str)) {
            throw new StatusCodeException(Status.create(msg.getCode(), msg.getDesc()));
        }
    }

    public static void notEmpty(String str, String template, Object... params) {
        if (StringUtils.isEmpty(str)) {
            throw new StatusCodeException(Status.error(format(template, params)));
        }
    }

    static String format(String template, Object... args) {
        if (args == null) {
            return template;
        } else {
            template = String.valueOf(template);
            StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
            int templateStart = 0;

            int i;
            int placeholderStart;
            for (i = 0; i < args.length; templateStart = placeholderStart + 2) {
                placeholderStart = template.indexOf("{}", templateStart);
                if (placeholderStart == -1) {
                    break;
                }

                builder.append(template.substring(templateStart, placeholderStart));
                builder.append(args[i++]);
            }

            builder.append(template.substring(templateStart));
            if (i < args.length) {
                builder.append(" [");
                builder.append(args[i++]);

                while (i < args.length) {
                    builder.append(", ");
                    builder.append(args[i++]);
                }

                builder.append(']');
            }

            return builder.toString();
        }
    }
}