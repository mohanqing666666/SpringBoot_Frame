package com.debug.steadyjack.listener;

import com.debug.steadyjack.model.entity.OrderRecord;
import com.debug.steadyjack.model.mapper.OrderRecordMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户商城下单-消息确认机制
 * Created by Administrator on 2018/8/29.
 */
@Component("userOrderListener")
public class UserOrderListener implements ChannelAwareMessageListener {

    private static final Logger log= LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRecordMapper orderRecordMapper;



    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag=message.getMessageProperties().getDeliveryTag();

        try {
            byte[] msg=message.getBody();
            OrderRecord orderRecord=objectMapper.readValue(msg, OrderRecord.class);
            log.info("用户商城下单监听到的消息：{} ",orderRecord);

//            int i=1/0;
//            this.realManageModule(orderRecord);


            channel.basicAck(tag,true);//确认消费
        }catch (Exception e){
            log.error("用户商城下单监听消息发生异常：",e.fillInStackTrace());

            channel.basicReject(tag,false);//确认消费
        }
    }

    //TODO：真正的业务模块
    private void realManageModule(OrderRecord orderRecord) throws Exception{
//        UserOrder userOrder=new UserOrder();
//        BeanUtils.copyProperties(userOrderDto,userOrder);
//        userOrderMapper.insertSelective(userOrder);
    }
}
































