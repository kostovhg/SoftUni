package realestateagency.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import realestateagency.utils.HtmlReader;
import realestateagency.utils.MapperUtil;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

//    @Bean
//    public ModelMapper mapper() {
//        return new ModelMapper();
//    }

    @Bean
    public HtmlReader htmlReader() {
        return new HtmlReader();
    }

    @Bean
    public MapperUtil mapperUtil() {
        return new MapperUtil();
    }
}
