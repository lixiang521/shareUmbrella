package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.OperationUser;
import com.blibee.umbrella.pojo.OperationUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OperationUserMapper {
    long countByExample(OperationUserExample example);

    int deleteByExample(OperationUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OperationUser record);

    int insertSelective(OperationUser record);

    List<OperationUser> selectByExampleWithRowbounds(OperationUserExample example, RowBounds rowBounds);

    List<OperationUser> selectByExample(OperationUserExample example);

    OperationUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OperationUser record, @Param("example") OperationUserExample example);

    int updateByExample(@Param("record") OperationUser record, @Param("example") OperationUserExample example);

    int updateByPrimaryKeySelective(OperationUser record);

    int updateByPrimaryKey(OperationUser record);
}