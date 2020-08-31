package com.debug.steadyjack.server;

import com.debug.steadyjack.model.entity.User;
import com.debug.steadyjack.request.EmployeeRequest;

import java.io.IOException;

public interface UserService {
   /*查询用户详情（radish测试）*/
   User getUserInfoV4(Integer userid) throws IOException;
   /*更新缓存数据*/
   void updateCache(Integer userId) throws IOException;
   /*用户详情-hash散列存储*/
   User getUserInfoV6(Integer userId) throws IOException;
   /*用户详情-hash散列存储*/
   User getUserInfoV5(Integer userId) throws IOException;

   void register(EmployeeRequest request) throws Exception;

   void registerV2(EmployeeRequest request) throws Exception;

   void registerV3(EmployeeRequest request) throws Exception;
}
