package com.blibee.umbrella.service;

import com.blibee.umbrella.api.json.JsonUtil;
import com.blibee.umbrella.common.util.WAssert;
import com.blibee.umbrella.dao.GlobalConfigMapper;
import com.blibee.umbrella.model.pojo.GlobalConfig;
import com.blibee.umbrella.model.pojo.GlobalConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixiang on 2019/04/27.
 */
@Service
public class GlobalConfigService {
    @Autowired
    private GlobalConfigMapper globalConfigMapper;

    /**
     * 新增全局配置
     *
     * @param name
     * @param context
     * @param note
     */
    public void add(String name, String context, String note) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setContext(context);
        globalConfig.setName(name);
        globalConfig.setNote(note);
        WAssert.isTrue(1 == globalConfigMapper.insertSelective(globalConfig), "添加全局配置参数失败");
    }

    /**
     * 雨伞硬件列表
     *
     * @return
     */
    public List<String> hardVers() {
        GlobalConfig hardVerStr = queryByName("umbrella_hard_ver");
        WAssert.notNull(hardVerStr, "全局配置不存在");
        return JsonUtil.of(hardVerStr.getContext(), List.class);
    }

    GlobalConfig queryByName(String name) {
        GlobalConfigExample globalConfigExample = new GlobalConfigExample();
        globalConfigExample.createCriteria().andNameEqualTo(name);
        return globalConfigMapper.selectByExample(globalConfigExample).stream().findFirst().orElse(null);
    }
}
