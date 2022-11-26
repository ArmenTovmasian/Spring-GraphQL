package com.tovmasian.springgraphql.config;

import org.dataloader.DataLoaderRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQlConfig {

    @Bean
    public DataLoaderRegistry createDataLoaderRegistry(){
        return new DataLoaderRegistry();
    }
}
