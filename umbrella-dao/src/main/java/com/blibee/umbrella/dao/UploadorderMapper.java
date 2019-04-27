package com.blibee.umbrella.dao;

import com.blibee.umbrella.model.pojo.Uploadorder;
import com.blibee.umbrella.model.pojo.UploadorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UploadorderMapper {
    long countByExample(UploadorderExample example);

    int deleteByExample(UploadorderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Uploadorder record);

    int insertSelective(Uploadorder record);

    List<Uploadorder> selectByExampleWithRowbounds(UploadorderExample example, RowBounds rowBounds);

    List<Uploadorder> selectByExample(UploadorderExample example);

    Uploadorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Uploadorder record, @Param("example") UploadorderExample example);

    int updateByExample(@Param("record") Uploadorder record, @Param("example") UploadorderExample example);

    int updateByPrimaryKeySelective(Uploadorder record);

    int updateByPrimaryKey(Uploadorder record);
}