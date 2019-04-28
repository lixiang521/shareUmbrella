package com.pro.umbrella.model.ro;

import java.util.List;

import com.pro.umbrella.api.pojo.BaseBean;

import lombok.Data;

/**
 * Created by lixiang on 2019/04/27.
 */
@Data
public class OperationUmbrellaListResp extends BaseBean {
    private static final long serialVersionUID = 4363677088562851598L;
    private String umbrellaNumber;
    private String umbrellaCabinetNumber;
    private String shopName;
    private Byte transState;
    private Byte onlineState;
    private Byte totalPosition;
    private Byte usablePosition;
    private List<UmbrellaInfo> umbrellas;
    private String hardVer;

    @Data
    public static class UmbrellaInfo {
        private String umbrellaNumber;
    }
}
