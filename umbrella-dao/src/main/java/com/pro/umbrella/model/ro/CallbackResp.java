package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longchaochen on 2017/10/31.
 */
@Data
public class CallbackResp<T> extends BaseBean {
    private static final long serialVersionUID = 946494952064036263L;
    private boolean ret;
    private int status;
    private String msg;
    private T data;

    public static <T> CallbackResp<T> success(T data) {
        CallbackResp<T> result = new CallbackResp<>();
        result.setRet(true);
        result.setStatus(0);
        result.setData(data);
        return result;
    }

    public static <T> CallbackResp<T> error(String message) {
        CallbackResp<T> result = new CallbackResp<>();
        result.setRet(false);
        result.setStatus(10000);
        result.setMsg(message);
        return result;
    }

}
