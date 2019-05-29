package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lixiang on 2019/04/27.
 */
@Getter
@Setter
public class OpUserRegisterReq extends BaseBean {
    private static final long serialVersionUID = 4373978759139368379L;
    private String username;
    private String password;
}
