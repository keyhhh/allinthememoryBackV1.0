package com.cyh.allinthememory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer MyWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")

                        //放行哪些原始域
                        .allowedOrigins("*")
                        .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                        .allowedHeaders("*")
                        .exposedHeaders("*");
            }
        };
    }
}