package com.debug.steadyjack.model.mapper;

import com.debug.steadyjack.model.entity.EsGonds;
import com.debug.steadyjack.model.entity.EsGondsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EsGondsMapper {
    int countByExample(EsGondsExample example);

    int deleteByExample(EsGondsExample example);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(EsGonds record);

    int insertSelective(EsGonds record);

    List<EsGonds> selectByExample(EsGondsExample example);

    EsGonds selectByPrimaryKey(Integer goodsId);

    int updateByExampleSelective(@Param("record") EsGonds record, @Param("example") EsGondsExample example);

    int updateByExample(@Param("record") EsGonds record, @Param("example") EsGondsExample example);

    int updateByPrimaryKeySelective(EsGonds record);

    int updateByPrimaryKey(EsGonds record);
}