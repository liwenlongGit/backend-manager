package com.lwl.backendmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@SpringBootApplication
@MapperScan(basePackages = {"com/lwl/backendmanager/authority/dao"})  // 扫描dao接口，为每个接口添加@Mapper注解，也可以手动添加
public class BackendManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendManagerApplication.class, args);
    }
}
