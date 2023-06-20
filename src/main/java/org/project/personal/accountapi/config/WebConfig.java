package org.project.personal.accountapi.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class WebConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer setJackson2ObjectMapperBuilderCustomizer(Jackson2ObjectMapperBuilderCustomizer customizer){

        customizer.customize(jackson2ObjectMapperBuilder());

        return customizer;
    }

    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        return builder;
    }
}
