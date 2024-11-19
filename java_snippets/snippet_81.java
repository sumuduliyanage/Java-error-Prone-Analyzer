package com.example.rest.custom;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Configuration
class CustomScopeConfig {

    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return factory -> factory.registerScope(SCOPE_CUCUMBER_GLUE, new SimpleThreadScope());

    }

}
