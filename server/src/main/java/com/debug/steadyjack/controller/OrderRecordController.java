package com.debug.steadyjack.controller;

import com.debug.steadyjack.enums.StatusCode;
import com.debug.steadyjack.model.entity.OrderRecord;
import com.debug.steadyjack.model.mapper.OrderRecordMapper;
import com.debug.steadyjack.reponse.BaseResponse;
import com.debug.steadyjack.request.OrderRecordInsertUpdateDto;
import com.debug.steadyjack.server.orderRecordServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "order")
public class OrderRecordController {
    private static final Logger log = LoggerFactory.getLogger(OrderRecordController.class);

    @Resource
    private OrderRecordMapper orderRecordMapper;

    @Resource
    private orderRecordServer orderRecordServer1;
    /*获取配置文件的两种方式*/
    @Autowired
    private Environment env;

    @Value("${sample.user.name}")
    private String name;

    @Value("${sample.user.age}")
    private Integer age;
    /***
     * 功能描述:案例查询1
     * @author MS
     * @date 2020-07-22
     */
    @RequestMapping(value = "/detail")
    public BaseResponse detail(@RequestParam Integer id){
        BaseResponse Result = new BaseResponse(StatusCode.Success);
        try{
            OrderRecord or =  orderRecordMapper.selectByPrimaryKey(id);
            Result.setData(or);
        }catch (Exception e){
            log.error("查询发生异常！",e.fillInStackTrace());
            Result = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return Result;
    }
    /***
     * 功能描述:案例查询2
     * @author MS
     * @date 2020-07-22
     */
    @RequestMapping(value = "/list")
    public BaseResponse list(){
        BaseResponse Result = new BaseResponse(StatusCode.Success);
        try{
            List<OrderRecord> or =  orderRecordMapper.list();
            Result.setData(or);
        }catch (Exception e){
            log.error("查询2发生异常！",e.fillInStackTrace());
            Result = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return Result;
    }

    /***
     * 功能描述:案例新增
     * @author MS
     * @date 2020-07-22
     */
    @RequestMapping(value = "/insert")
    public BaseResponse insert(@RequestBody OrderRecordInsertUpdateDto or){
        BaseResponse Result = new BaseResponse(StatusCode.Success);
        try{
            log.info("接收数据：{} ",or);

            //TODO：进行属于你自己的控制层层面的校验以及处理
            orderRecordServer1.insert(or);
        }catch (Exception e){
            log.error("查询2发生异常！",e.fillInStackTrace());
            Result = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return Result;
    }
    /***
     * 功能描述:案例更新
     * @author MS
     * @date 2020-07-23
     */

    @RequestMapping(value = "/update")
    public BaseResponse update(@RequestBody OrderRecordInsertUpdateDto or){
        BaseResponse Result = new BaseResponse(StatusCode.Success);
        try{
            log.info("接收数据：{} ",or);

            //TODO：进行属于你自己的控制层层面的校验以及处理
            OrderRecord entity=orderRecordMapper.selectByPrimaryKey(or.getId());
            if (entity==null){
                return new BaseResponse(StatusCode.Entity_Not_Exist);
            }
            orderRecordServer1.update(entity,or);
        }catch (Exception e){
            log.error("查询2发生异常！",e.fillInStackTrace());
            Result = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return Result;
    }
    /***
     * 功能描述:配置文件获取验证
     * @author MS
     * @date 2020-08-03
     */
    @RequestMapping(value = "/getApplication")
    public BaseResponse getApplication(){
        BaseResponse Result = new BaseResponse(StatusCode.Success);
        try{
            Map map =new HashMap<>();
            map.put("id",env.getProperty("sample.user.id"));
            map.put("name",name);
            map.put("age",age);
            Result.setData(map);
        }catch (Exception e){
            log.error("查询2发生异常！",e.fillInStackTrace());
            Result = new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return Result;
    }


}
