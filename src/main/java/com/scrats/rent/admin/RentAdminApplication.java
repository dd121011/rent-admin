package com.scrats.rent.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.scrats.rent.admin.mapper")
@EnableCaching//启用缓存
public class RentAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentAdminApplication.class, args);
    }
}
