package com.example.electricity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.electricity.mapper")
@SpringBootApplication
public class ElectricityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricityApplication.class, args);
    }

}
