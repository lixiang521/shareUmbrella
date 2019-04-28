package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;

import lombok.Data;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaAddReq extends BaseBean {
    private static final long serialVersionUID = 4373978759139368379L;
    private String umbrellaNumber;
    private String umbrellaCabinetNumber;
    private Byte transState;
    private String hardVer;
}
