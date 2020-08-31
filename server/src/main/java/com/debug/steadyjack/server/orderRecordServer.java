package com.debug.steadyjack.server;

import com.debug.steadyjack.model.entity.OrderRecord;
import com.debug.steadyjack.request.OrderRecordInsertUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface orderRecordServer {
    /*插入*/
    int insert(OrderRecordInsertUpdateDto or);
    /*更新*/
    int update(OrderRecord orderRecord, OrderRecordInsertUpdateDto or);


}
