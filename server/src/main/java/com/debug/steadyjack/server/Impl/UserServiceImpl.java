package com.debug.steadyjack.server.Impl;

import com.debug.steadyjack.listener.event.UserRegisterEvent;
import com.debug.steadyjack.model.entity.User;
import com.debug.steadyjack.model.mapper.UserMapper;
import com.debug.steadyjack.rabbitmq.message.UserRegisterMessage;
import com.debug.steadyjack.request.EmployeeRequest;
import com.debug.steadyjack.server.EmailService;
import com.debug.steadyjack.server.UserService;
import com.debug.steadyjack.util.AESUtil;
import com.debug.steadyjack.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);
    @Autowired
    private Environment env;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EmailService mailService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public User getUserInfoV6(Integer userid) throws IOException {
        /*建立radish KEY值键*/
        final String key = String.format(env.getProperty("redis.user.info.key"),userid);
        User user = null;
        if(stringRedisTemplate.hasKey(key)){
            //TODO：key存在于缓存
            String value = stringRedisTemplate.opsForValue().get(key);
            if(!Strings.isNullOrEmpty(value)){
                user = objectMapper.readValue(value,User.class);
            }

        }else{
            //TODO：key不存在于缓存
            user = userMapper.selectByPrimaryKey(userid);
            Long expire= RandomUtils.nextLong(10,30);
            if(user != null){
                stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(user),expire, TimeUnit.MINUTES);
            }else{
                stringRedisTemplate.opsForValue().set(key,"",expire,TimeUnit.SECONDS);
            }
            log.info("过期时间{}"+expire);
        }

        return user;
    }

    @Override
    public void updateCache(Integer userId) throws IOException {
        User user = userMapper.selectByPrimaryKey(userId);
        try{
            if(user != null){
                Long expire= RandomUtils.nextLong(10,30);
                final String key = String.format(env.getProperty("redis.user.info.key"),userId);
                stringRedisTemplate.opsForValue().set(key,objectMapper.writeValueAsString(user),expire,TimeUnit.MINUTES);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User getUserInfoV5(Integer userId) throws IOException {
        final String key = env.getProperty("redis.user.info.hash.key");
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        Set<String> value1 = redisUtil.keys(key);
        User user = null;
        if(hashOperations.hasKey(key, String.valueOf(userId))){
            String value = hashOperations.get(key, String.valueOf(userId));
            if(!Strings.isNullOrEmpty(value)){
                user = objectMapper.readValue(value, User.class);
            }

        }else{
            user = userMapper.selectByPrimaryKey(userId);
            if(user == null){
                /*把空值塞在缓存里以防缓存穿透*/
                hashOperations.putIfAbsent(key, String.valueOf(userId), "");
            }else {
                hashOperations.putIfAbsent(key, String.valueOf(userId), objectMapper.writeValueAsString(user));
            }
        }
        return user;
    }

    @Override
    public User getUserInfoV4(Integer userId) throws IOException {
        final String key = env.getProperty("redis.user.info.hash.key");
        User user = new User();
        if(redisUtil.hasKey(key)){
            Object value = redisUtil.hget(key, String.valueOf(userId));
            if(value != null){
//                BeanUtils.copyProperties(value, user);
              user =  objectMapper.convertValue(value, User.class);
            }

        }else{
            user = userMapper.selectByPrimaryKey(userId);
            if(user == null){
                /*把空值塞在缓存里以防缓存穿透*/
                redisUtil.hset(key, String.valueOf(userId), "");
            }else {
                redisUtil.hset(key, String.valueOf(userId), user);
            }
        }
        return user;
    }


    /**
     * 注册信息
     * @param request
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(EmployeeRequest request) throws Exception{
        //TODO：录入信息
        User user=new User();
        BeanUtils.copyProperties(request,user);
        userMapper.insertSelective(user);

        //TODO：更新缓存
        this.updateCache(user.getId());

        //TODO：发送邮件
        Map<String,Object> paramsMap= Maps.newHashMap();
        paramsMap.put("userName",user.getUserName());
        paramsMap.put("url","http://www.baidu.com");
        String html=mailService.renderTemplate(env.getProperty("mail.template.file.location.register"),paramsMap);
        mailService.sendHTMLMail("成功入职通知",html,new String[]{user.getEmail()});
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerV2(EmployeeRequest request) throws Exception{
        //TODO：录入信息
        User user=new User();
        BeanUtils.copyProperties(request,user);
        userMapper.insertSelective(user);

        //TODO：异步发送消息
        String url=env.getProperty("system.common.config.domain")+"/spring/asynchronous/register/validate?";
        Long timestamp=System.currentTimeMillis();

        Map<String,String> dataMap=new HashMap<String, String>();
        dataMap.put("userName",user.getUserName());
        dataMap.put("timestamp",String.valueOf(timestamp));
        String dataMapStr=objectMapper.writeValueAsString(dataMap);
        String encryptStr= URLEncoder.encode(AESUtil.encrypt(dataMapStr),"utf-8");
        log.info("加密后的串：{} ",encryptStr);

        String params=String.format("userName=%s&timestamp=%s&encryptStr=%s",user.getUserName(),timestamp,encryptStr);
        UserRegisterEvent event=new UserRegisterEvent(this,user,url+params);
        publisher.publishEvent(event);
    }


    /**
     * rabbitmq-消息异步通信-业务服务异步解耦
     * @param request
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerV3(EmployeeRequest request) throws Exception{
        //TODO：录入信息
        User user=new User();
        BeanUtils.copyProperties(request,user);
        userMapper.insertSelective(user);

        //TODO：异步发送消息
        String url=env.getProperty("system.common.config.domain")+"/spring/asynchronous/register/validate?";
        Long timestamp=System.currentTimeMillis();

        Map<String,String> dataMap=new HashMap<String, String>();
        dataMap.put("userName",user.getUserName());
        dataMap.put("timestamp",String.valueOf(timestamp));
        String dataMapStr=objectMapper.writeValueAsString(dataMap);
        String encryptStr=URLEncoder.encode(AESUtil.encrypt(dataMapStr),"utf-8");

        String params=String.format("userName=%s&timestamp=%s&encryptStr=%s",user.getUserName(),timestamp,encryptStr);

        //TODO：发送消息
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        UserRegisterMessage message=new UserRegisterMessage(user,url+params);
        Message msg= MessageBuilder.withBody(objectMapper.writeValueAsBytes(message)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        rabbitTemplate.send(env.getProperty("rabbitmq.user.register.exchange.name"),env.getProperty("rabbitmq.user.register.routing.key.name"),msg);

        //TODO：加上30min的限制-开发时可以采用3min
        final Long expire=3L;
        final String key=user.getUserName() + String.valueOf(timestamp);
        stringRedisTemplate.opsForValue().set(key,user.getUserName(),expire,TimeUnit.MINUTES);
    }



}
