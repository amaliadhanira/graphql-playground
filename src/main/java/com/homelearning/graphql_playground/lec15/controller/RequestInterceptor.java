package com.homelearning.graphql_playground.lec15.controller;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public class RequestInterceptor implements WebGraphQlInterceptor {


    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        var headers = request.getHeaders().getOrEmpty("caller-id");
        var callerId = headers.isEmpty() ? "": headers.get(0);
        request.configureExecutionInput((e, b) -> b.graphQLContext(Map.of("caller-id", callerId)).build());
        return chain.next(request);
    }
}
