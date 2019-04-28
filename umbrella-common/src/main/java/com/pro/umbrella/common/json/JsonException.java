package com.pro.umbrella.common.json;

import org.springframework.core.NestedRuntimeException;

/**
 * JSON异常。
 * Created by lixiang on 2019/04/27.
 */
public class JsonException extends NestedRuntimeException {

    private static final long serialVersionUID = -9198606590046525595L;

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
