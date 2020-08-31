package com.debug.steadyjack.model.mapper;

import com.debug.steadyjack.model.entity.Appendix;
import com.debug.steadyjack.model.entity.AppendixExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppendixMapper {
    int countByExample(AppendixExample example);

    int deleteByExample(AppendixExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Appendix record);

    int insertSelective(Appendix record);

    List<Appendix> selectByExample(AppendixExample example);

    Appendix selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Appendix record, @Param("example") AppendixExample example);

    int updateByExample(@Param("record") Appendix record, @Param("example") AppendixExample example);

    int updateByPrimaryKeySelective(Appendix record);

    int updateByPrimaryKey(Appendix record);

    List<Appendix> selectModuleAppendix(@Param("moduleType") String moduleType,@Param("recordId") Integer recordId);

    List<Appendix> selectModuleAppendixV2(@Param("moduleType") String moduleType,@Param("recordId") Integer recordId,@Param("rootUrl") String rootUrl);
}