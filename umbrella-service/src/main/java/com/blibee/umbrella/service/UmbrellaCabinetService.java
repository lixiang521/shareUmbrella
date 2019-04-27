package com.blibee.umbrella.service;

import com.blibee.umbrella.dao.UmbrellaCabinetMapper;
import com.blibee.umbrella.model.pojo.UmbrellaCabinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UmbrellaCabinetService {
    @Autowired
    private UmbrellaCabinetMapper umbrellaCabinetMapper;

    public boolean add(UmbrellaCabinet umbrellaCabinet) {
        return umbrellaCabinetMapper.insertSelective(umbrellaCabinet) > 0;
    }
}
