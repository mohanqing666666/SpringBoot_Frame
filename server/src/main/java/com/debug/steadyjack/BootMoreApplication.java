package com.debug.steadyjack;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableCaching
//@EnableScheduling
/*数据库配置*/
@ImportResource(locations = "classpath:spring/spring-jdbc.xml")
/*通过使用@MapperScan可以指定要扫描的Mapper类的包的路径*/
@MapperScan(basePackages = "com.debug.steadyjack.model.mapper")
@EnableScheduling
public class BootMoreApplication extends SpringBootServletInitializer {

    @Autowired
    private Environment env;

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }

    //不使用spring boot内嵌tomcat启动方式
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootMoreApplication.class);
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(BootMoreApplication.class, args);
    }

}


