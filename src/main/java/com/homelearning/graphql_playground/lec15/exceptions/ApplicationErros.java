package com.homelearning.graphql_playground.lec15.exceptions;

import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

import java.util.Map;

public class ApplicationErros {

    public static <T> Mono<T> noSuchUser(Integer id){
        return Mono.error(new ApplicationExceptions(
                ErrorType.BAD_REQUEST, "No such user", Map.of(
                        "customerId", id
        )
        ));
    }
}
