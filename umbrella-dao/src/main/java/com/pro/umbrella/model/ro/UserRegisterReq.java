package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lixiang on 2019/04/27.
 */
@Getter
@Setter
public class UserRegisterReq extends BaseBean {
    private static final long serialVersionUID = 4373978759139368379L;
    private String phone;
    private String password;
}
