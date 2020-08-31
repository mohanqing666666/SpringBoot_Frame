package com.debug.steadyjack.server.Impl;

import com.debug.steadyjack.model.entity.OrderRecord;
import com.debug.steadyjack.model.mapper.OrderRecordMapper;
import com.debug.steadyjack.request.OrderRecordInsertUpdateDto;
import com.debug.steadyjack.server.orderRecordServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class orderRecordServerImpl implements orderRecordServer {
    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(OrderRecordInsertUpdateDto or) {
        //TODO：采用spring提供的BeanUtils进行操作
        OrderRecord record=new OrderRecord();
        BeanUtils.copyProperties(or,record);
        return orderRecordMapper.insert(record);
    }

    @Override
    public int update(OrderRecord orderRecord, OrderRecordInsertUpdateDto or) {
        //TODO：采用spring提供的BeanUtils进行操作
        final Integer pk=orderRecord.getId();
        BeanUtils.copyProperties(or,orderRecord);
        orderRecord.setUpdateTime(new Date());
        orderRecord.setId(pk);
        return orderRecordMapper.updateByPrimaryKey(orderRecord);
    }
}
