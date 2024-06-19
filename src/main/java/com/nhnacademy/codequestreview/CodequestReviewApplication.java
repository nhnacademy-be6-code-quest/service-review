package com.nhnacademy.codequestreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CodequestReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodequestReviewApplication.class, args);
    }

}
