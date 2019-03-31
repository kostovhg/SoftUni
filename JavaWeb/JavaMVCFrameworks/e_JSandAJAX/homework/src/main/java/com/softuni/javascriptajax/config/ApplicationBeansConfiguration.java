package com.softuni.javascriptajax.config;

import com.softuni.javascriptajax.utils.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansConfiguration {

    @Bean
    public MapperUtil mapper(){
        return new MapperUtil();
    }
}
