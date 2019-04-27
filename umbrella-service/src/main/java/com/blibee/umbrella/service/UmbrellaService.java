package com.blibee.umbrella.service;

import com.blibee.umbrella.api.pojo.page.PageRequest;
import com.blibee.umbrella.api.pojo.page.Pager;
import com.blibee.umbrella.common.util.WAssert;
import com.blibee.umbrella.dao.UmbrellaMapper;
import com.blibee.umbrella.model.constants.TransferState;
import com.blibee.umbrella.model.pojo.Umbrella;
import com.blibee.umbrella.model.pojo.UmbrellaCabinet;
import com.blibee.umbrella.model.pojo.UmbrellaExample;
import com.blibee.umbrella.model.ro.OperationUmbrellaAddReq;
import com.blibee.umbrella.model.ro.OperationUmbrellaListResp;
import com.blibee.umbrella.model.ro.OperationUmbrellaPageReq;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lixiang on 2019/04/27.
 */
@Service
public class UmbrellaService {
    @Autowired
    private UmbrellaMapper umbrellaMapper;
    @Autowired
    private UmbrellaCabinetService umbrellaCabinetService;

    /**
     * 新增雨伞
     *
     * @param req
     */
    public void add(OperationUmbrellaAddReq req) {
        if (StringUtils.isNotEmpty(req.getUmbrellaCabinetNumber())) {
            // TODO 需要根据某种状态（如流转状态）确定是否是新增雨伞，否则设备回调会认为是还
            UmbrellaCabinet umbrellaCabinet = umbrellaCabinetService.queryByCabinetId(req.getUmbrellaCabinetNumber());
            WAssert.notNull(umbrellaCabinet, "雨伞柜不存在");
        }
        Umbrella umbrella = new Umbrella();
        umbrella.setRepairState(TransferState.UmbrellaRepairState.NORMAL);
        BeanUtils.copyProperties(req, umbrella);
        WAssert.isTrue(1 == umbrellaMapper.insertSelective(umbrella), "添加雨伞失败");
    }

    /**
     * 更新雨伞
     *
     * @param req
     */
    public void update(OperationUmbrellaAddReq req) {
        WAssert.hasLength(req.getUmbrellaNumber(), "雨伞id不能为空");
        Umbrella umbrellaNew = new Umbrella();
        BeanUtils.copyProperties(req, umbrellaNew);
        WAssert.isTrue(1 == updateByDeviceId(umbrellaNew), "更新雨伞失败");
    }

    /**
     * 雨伞列表（分页）
     *
     * @param req
     * @return
     */
    public Pager list(OperationUmbrellaPageReq req) {
        int count = countList(req.getUmbrellaNumber(), req.getTransState(), req.getHardVer());
        if (count == 0) {
            return Pager.builder(Collections.emptyList()).total(count).current(req.getPage()).create();
        }

        List<Umbrella> umbrellas = queryList(req.getUmbrellaNumber(), req.getTransState(),
                req.getHardVer(), req);
        List<OperationUmbrellaListResp> resps = umbrellas.stream().map(umbrella -> {
            OperationUmbrellaListResp resp = new OperationUmbrellaListResp();
            BeanUtils.copyProperties(umbrella, resp);
            return resp;
        }).collect(Collectors.toList());

        return Pager.builder(resps).total(count).current(req.getPage()).create();
    }

    /**
     * 条件查询结果条数
     *
     * @param umbrellaNumber
     * @param transState
     * @param hardVer
     * @return
     */
    int countList(String umbrellaNumber, Byte transState, String hardVer) {
        UmbrellaExample example = createListExample(umbrellaNumber, transState, hardVer);
        return (int) umbrellaMapper.countByExample(example);
    }

    /**
     * 条件查询列表
     *
     * @param umbrellaNumber
     * @param transState
     * @param hardVer
     * @param page
     * @return
     */
    public List<Umbrella> queryList(String umbrellaNumber, Byte transState, String hardVer, PageRequest page) {
        UmbrellaExample example = new UmbrellaExample();
        UmbrellaExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(umbrellaNumber)) {
            criteria.andUmbrellaNumberEqualTo(umbrellaNumber);
        }
        if (transState != null) {
            criteria.andTransStateEqualTo(transState);
        }
        if (StringUtils.isNotBlank(hardVer)) {
            criteria.andHardVerEqualTo(hardVer);
        }
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getLimit());
        return umbrellaMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    /**
     * 拼接条件Example
     *
     * @param UmbrellaNumber
     * @param transState
     * @param hardVer
     * @return
     */
    private UmbrellaExample createListExample(String UmbrellaNumber, Byte transState, String hardVer) {
        UmbrellaExample example = new UmbrellaExample();
        UmbrellaExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(UmbrellaNumber)) {
            criteria.andUmbrellaNumberEqualTo(UmbrellaNumber);
        }
        if (transState != null) {
            criteria.andTransStateEqualTo(transState);
        }
        if (StringUtils.isNotEmpty(hardVer)) {
            criteria.andHardVerEqualTo(hardVer);
        }
        return example;
    }

    /**
     * 根据雨伞id更新
     *
     * @param record
     * @return
     */
    private int updateByDeviceId(Umbrella record) {
        UmbrellaExample example = new UmbrellaExample();
        example.createCriteria().andUmbrellaNumberEqualTo(record.getUmbrellaNumber());
        record.setUpdateTime(new Date());
        return umbrellaMapper.updateByExampleSelective(record, example);
    }
}
