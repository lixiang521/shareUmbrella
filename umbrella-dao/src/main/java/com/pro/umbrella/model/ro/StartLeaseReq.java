package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import lombok.Data;

/**
 * Created by longlong.zhang on 2018/3/29
 */
@Data
public class StartLeaseReq extends BaseBean {
    private static final long serialVersionUID = -3372262085484872448L;
    private Long leaseNumber;
    // 重试原因 0:首次下发 1：伞柜占用重试 2：伞柜不在线重试 3：租赁失败重试 4：租赁超时重试 ...
    private byte retryReson;
}
