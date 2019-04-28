package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.page.PageRequest;

import lombok.Data;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaPageReq extends PageRequest {
    private static final long serialVersionUID = -5388238639532087021L;
    private String umbrellaNumber;
    private Byte transState;
    private String hardVer;
}
