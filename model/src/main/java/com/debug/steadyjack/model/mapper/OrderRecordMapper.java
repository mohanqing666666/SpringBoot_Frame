package com.debug.steadyjack.model.mapper;

import com.debug.steadyjack.model.entity.OrderRecord;
import com.debug.steadyjack.model.entity.OrderRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRecordMapper {
    int countByExample(OrderRecordExample example);

    int deleteByExample(OrderRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRecord record);

    int insertSelective(OrderRecord record);

    List<OrderRecord> selectByExample(OrderRecordExample example);

    OrderRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderRecord record, @Param("example") OrderRecordExample example);

    int updateByExample(@Param("record") OrderRecord record, @Param("example") OrderRecordExample example);

    int updateByPrimaryKeySelective(OrderRecord record);

    int updateByPrimaryKey(OrderRecord record);

    List<OrderRecord> list();
}