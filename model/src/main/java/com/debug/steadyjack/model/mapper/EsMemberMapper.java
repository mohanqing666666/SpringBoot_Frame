package com.debug.steadyjack.model.mapper;

import com.debug.steadyjack.model.entity.EsMember;
import com.debug.steadyjack.model.entity.EsMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EsMemberMapper {
    int countByExample(EsMemberExample example);

    int deleteByExample(EsMemberExample example);

    int deleteByPrimaryKey(Integer memberId);

    int insert(EsMember record);

    int insertSelective(EsMember record);

    List<EsMember> selectByExample(EsMemberExample example);

    EsMember selectByPrimaryKey(Integer memberId);

    int updateByExampleSelective(@Param("record") EsMember record, @Param("example") EsMemberExample example);

    int updateByExample(@Param("record") EsMember record, @Param("example") EsMemberExample example);

    int updateByPrimaryKeySelective(EsMember record);

    int updateByPrimaryKey(EsMember record);
}