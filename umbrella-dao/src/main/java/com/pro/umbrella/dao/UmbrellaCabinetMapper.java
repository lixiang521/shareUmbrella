package com.pro.umbrella.dao;

import com.pro.umbrella.model.pojo.UmbrellaCabinet;
import com.pro.umbrella.model.pojo.UmbrellaCabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmbrellaCabinetMapper {
    long countByExample(UmbrellaCabinetExample example);

    int deleteByExample(UmbrellaCabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UmbrellaCabinet record);

    int insertSelective(UmbrellaCabinet record);

    List<UmbrellaCabinet> selectByExampleWithRowbounds(UmbrellaCabinetExample example, RowBounds rowBounds);

    List<UmbrellaCabinet> selectByExample(UmbrellaCabinetExample example);

    UmbrellaCabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UmbrellaCabinet record, @Param("example") UmbrellaCabinetExample example);

    int updateByExample(@Param("record") UmbrellaCabinet record, @Param("example") UmbrellaCabinetExample example);

    int updateByPrimaryKeySelective(UmbrellaCabinet record);

    int updateByPrimaryKey(UmbrellaCabinet record);
}