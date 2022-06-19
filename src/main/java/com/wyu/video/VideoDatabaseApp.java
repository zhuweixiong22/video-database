package com.wyu.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author zwx
 * @date 2022-06-18 0:16
 */
@SpringBootApplication
@MapperScan(value = "com.wyu.video.mapper")
public class VideoDatabaseApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(VideoDatabaseApp.class, args);
    }
}
