package com.debug.steadyjack.controller;

import com.debug.steadyjack.enums.StatusCode;
import com.debug.steadyjack.model.entity.User;
import com.debug.steadyjack.model.mapper.UserMapper;
import com.debug.steadyjack.reponse.BaseResponse;
import com.debug.steadyjack.request.EmployeeRequest;
import com.debug.steadyjack.server.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RadisTestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    private static final Logger log= LoggerFactory.getLogger(RadisTestController.class);

    private static final String prefix="user";


/***
 * 功能描述:通过查询用户详情测试redis
 * @author MS
 * @date 2020-08-18
 */
    @RequestMapping(value = prefix+"/detail/{userid}", method = RequestMethod.GET)
    public BaseResponse detail(@PathVariable Integer userid){
        BaseResponse Response = new BaseResponse(StatusCode.Success);
        if(userid <= 0){
            return new BaseResponse(StatusCode.Invalid_Params);
        }
        try{
            Response.setData(userService.getUserInfoV4(userid));
        }catch(Exception e){
            Response =new  BaseResponse(StatusCode.Fail);
            e.printStackTrace();
        }

         return  Response;
    }
/***
 * 功能描述:更新操作测试Redis
 * @author MS
 * @date 2020-08-18
 */
    @RequestMapping(value = prefix +"/insert/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse insertUpdate(@RequestBody @Validated EmployeeRequest employeeRequest, BindingResult bindingResult){
        BaseResponse Response = new BaseResponse(StatusCode.Success);
        if(bindingResult.hasErrors()){
            return new BaseResponse(StatusCode.Invalid_Params);
        }
        try {
            if(employeeRequest.getId()!=null && employeeRequest.getId()>0){
                User user = userMapper.selectByPrimaryKey(employeeRequest.getId());
                BeanUtils.copyProperties(employeeRequest,user);
                userMapper.updateByPrimaryKeySelective(user);
                userService.updateCache(user.getId());
            }else {
                User user = new User();
                BeanUtils.copyProperties(employeeRequest,user);
                userMapper.insertSelective(user);
                userService.updateCache(user.getId());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response;
    }
}
