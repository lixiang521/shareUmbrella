package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.BaseBean;
import com.pro.umbrella.model.pojo.HandleResult;
import com.pro.umbrella.model.pojo.WorkOrder;
import lombok.Data;

import java.util.List;

/**
 * Created by longchaochen on 2017/10/31.
 */
@Data
public class WorkOrderResp extends BaseBean {
   private WorkOrder workOrder;
   private List<HandleResult> handleResult;
}
