package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.page.PageRequest;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Created by longchaochen on 2017/10/25.
 */
@Data
public class OperationLeasePageReq extends PageRequest {
    private static final long serialVersionUID = 722142798877226798L;
    private String id; // 订单号
    private String umbrellaNumber; // 雨伞编号
    private String startCabinetNumber; // 借出伞柜
    private String endCabinetNumber; // 归还伞柜
    private String uid; // 用户ID
    private String leaseState; // 订单状态
    private String tradeState; // 支付状态

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String minCreateTime; // 下单时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String maxCreateTime; // 下单时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String minReturnTime; // 还伞时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String maxReturnTime; // 还伞时间

    private String startScene; // 借出场景
    private String startCity; // 借出市
    private String endCity;
    private String leaseNode;

    // 前端
    private String[] leaseStateArray; // 订单状态
    private String[] tradeStateArray; // 支付状态
    private String[] startSceneTypeArray;
    private String[] startSceneArray;
    private String[] startCityArray;
    private String[] endSceneTypeArray;
    private String[] endSceneArray;
    private String[] endCityArray;
    private String[] codeArray;
    private String cabinetDetailNum;
    private String[] entranceArray;
    private String[] payTypeArray;
}
