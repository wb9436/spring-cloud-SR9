package com.ivan.jpa.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;

/**
 * JPA配置类
 *
 * @author WuBing
 * @date 2022-05-08 22:30
 */
@Configuration
public class JpaConfig {

    @PostConstruct
    public void init() {
        GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());
        genericConversionService.addConverter(new JpaMapToDtoConvert());
    }


}
