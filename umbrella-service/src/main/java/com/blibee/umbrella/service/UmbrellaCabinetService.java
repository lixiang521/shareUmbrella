package com.blibee.umbrella.service;

import com.blibee.umbrella.common.util.WAssert;
import com.blibee.umbrella.dao.UmbrellaCabinetMapper;
import com.blibee.umbrella.model.constants.Constants;
import com.blibee.umbrella.model.pojo.UmbrellaCabinet;
import com.blibee.umbrella.model.pojo.UmbrellaCabinetExample;
import com.blibee.umbrella.model.ro.OperationUmbrellaCabinetAddReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@Service
public class UmbrellaCabinetService {
    @Autowired
    private UmbrellaCabinetMapper umbrellaCabinetMapper;

    /**
     * 新增雨伞柜
     *
     * @param req
     */
    public void add(OperationUmbrellaCabinetAddReq req) {

        UmbrellaCabinet umbrellaCabinet = new UmbrellaCabinet();
        BeanUtils.copyProperties(req, umbrellaCabinet);
        umbrellaCabinet.setMaxCap(Constants.MAX_CAP_INTERNAL);
        umbrellaCabinet.setPropertyCap((byte) (Constants.MAX_CAP_INTERNAL - 1));
        WAssert.isTrue(1 == umbrellaCabinetMapper.insertSelective(umbrellaCabinet), "添加雨伞柜失败");
    }

    /**
     * 根据伞柜id查询伞柜
     *
     * @param cabinetId
     * @return
     */
    public UmbrellaCabinet queryByCabinetId(String cabinetId) {
        if (StringUtils.isBlank(cabinetId)) {
            return null;
        }
        UmbrellaCabinetExample example = new UmbrellaCabinetExample();
        example.createCriteria().andUmbrellaCabinetNumberEqualTo(cabinetId);
        List<UmbrellaCabinet> umbrellaCabinets = umbrellaCabinetMapper.selectByExample(example);
        return (umbrellaCabinets != null && !umbrellaCabinets.isEmpty()) ? umbrellaCabinets.get(0) : null;
    }
}
