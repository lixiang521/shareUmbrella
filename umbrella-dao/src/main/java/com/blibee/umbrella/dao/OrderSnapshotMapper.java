package com.blibee.umbrella.dao;

import com.blibee.umbrella.pojo.OrderSnapshot;
import com.blibee.umbrella.pojo.OrderSnapshotExample;
import com.blibee.umbrella.pojo.OrderSnapshotWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderSnapshotMapper {
    long countByExample(OrderSnapshotExample example);

    int deleteByExample(OrderSnapshotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderSnapshotWithBLOBs record);

    int insertSelective(OrderSnapshotWithBLOBs record);

    List<OrderSnapshotWithBLOBs> selectByExampleWithBLOBsWithRowbounds(OrderSnapshotExample example, RowBounds rowBounds);

    List<OrderSnapshotWithBLOBs> selectByExampleWithBLOBs(OrderSnapshotExample example);

    List<OrderSnapshot> selectByExampleWithRowbounds(OrderSnapshotExample example, RowBounds rowBounds);

    List<OrderSnapshot> selectByExample(OrderSnapshotExample example);

    OrderSnapshotWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderSnapshotWithBLOBs record, @Param("example") OrderSnapshotExample example);

    int updateByExampleWithBLOBs(@Param("record") OrderSnapshotWithBLOBs record, @Param("example") OrderSnapshotExample example);

    int updateByExample(@Param("record") OrderSnapshot record, @Param("example") OrderSnapshotExample example);

    int updateByPrimaryKeySelective(OrderSnapshotWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OrderSnapshotWithBLOBs record);

    int updateByPrimaryKey(OrderSnapshot record);
}