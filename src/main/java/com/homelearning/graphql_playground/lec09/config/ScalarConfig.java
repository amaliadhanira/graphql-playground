package com.homelearning.graphql_playground.lec09.config;
import graphql.scalars.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class ScalarConfig {

    @Bean
    public RuntimeWiringConfigurer configurer(){
        return c -> c
                .scalar(ExtendedScalars.GraphQLLong)
                .scalar(ExtendedScalars.GraphQLShort)
                .scalar(ExtendedScalars.GraphQLByte)
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.GraphQLBigInteger)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.LocalTime)
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Object);
    }

    /*
     private Long distance;
    private Byte ageInYears;
    private Short ageInMonths;
    private BigDecimal bigDecimal;
    private BigInteger bigInteger;
    private LocalDate date;
    private LocalTime time;
    private OffsetDateTime dateTime;
     */
}
