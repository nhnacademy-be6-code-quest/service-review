//package com.nhnacademy.codequestreview.config;
//
//
//import java.io.File;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/uploads/**")
//            .addResourceLocations(
//                "file:" + new File("src/main/resources/static/uploads").getAbsolutePath() + "/");
//    }
//
//}
