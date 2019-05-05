package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longchaochen on 2017/10/31.
 */
@Data
public class UserInfoResp extends BaseBean {
    private Long uid;
    private String phone;
    public UserInfoResp(Long uid,String phone){
        this.phone=phone;
        this.uid=uid;
    }
}
