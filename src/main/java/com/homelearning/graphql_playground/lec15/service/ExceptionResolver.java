package com.homelearning.graphql_playground.lec15.service;

import com.homelearning.graphql_playground.lec15.exceptions.ApplicationExceptions;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ex = toApplicationException(exception);
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError(environment)
                                .message(ex.getMessage())
                                .errorType(ex.getErrorType())
                                .extensions(ex.getExtensions())
                                .build()
                )
        );
    }

    private ApplicationExceptions toApplicationException(Throwable throwable){
        return ApplicationExceptions.class.equals(throwable.getClass()) ? (ApplicationExceptions) throwable : new ApplicationExceptions(ErrorType.INTERNAL_ERROR, throwable.getMessage(), Collections.emptyMap());
    }
}
