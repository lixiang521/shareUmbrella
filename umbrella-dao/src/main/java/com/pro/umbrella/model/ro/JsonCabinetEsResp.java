package com.pro.umbrella.model.ro;

import com.pro.umbrella.api.pojo.Status;
import com.pro.umbrella.api.pojo.page.Pager;
import lombok.Data;

/**
 * json数据
 *
 * @author longlong.zhang
 * @since 30 十月 2018
 */
@Data
public class JsonCabinetEsResp {

    protected Pager<OperationCabinetEsResp> data;
    protected int status;
    protected String msg;

    public JsonCabinetEsResp() {
    }

    public boolean isSuccess() {
        return Status.create(this.status, this.msg).isSuccess();
    }
}
