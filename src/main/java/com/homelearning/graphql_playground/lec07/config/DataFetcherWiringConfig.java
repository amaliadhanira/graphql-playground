package com.homelearning.graphql_playground.lec07.config;

import com.homelearning.graphql_playground.lec07.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Map;

@Configuration
public class DataFetcherWiringConfig {

    @Autowired
    private CustomerOrderDataFetcher dataFetcher;

    @Bean
    public RuntimeWiringConfigurer configurer(){
        return c -> c.type("Query", b -> b.dataFetchers(map()));
    }

    private Map<String, DataFetcher> map(){
        return Map.of(
                "customers", dfe -> "string",
                "age", dfe -> 12,
                "city", dfe -> "Solo"
        );
    }
}
