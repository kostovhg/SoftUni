package com.softuni.exodia.config;

import com.softuni.exodia.utils.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public MapperUtil mapper() {
        return new MapperUtil();
    }
}
