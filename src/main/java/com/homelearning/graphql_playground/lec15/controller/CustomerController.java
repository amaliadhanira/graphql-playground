package com.homelearning.graphql_playground.lec15.controller;

import com.homelearning.graphql_playground.lec15.dto.CustomerDto;
import com.homelearning.graphql_playground.lec15.dto.CustomerNotFound;
import com.homelearning.graphql_playground.lec15.dto.DeleteResponseDto;
import com.homelearning.graphql_playground.lec15.exceptions.ApplicationErros;
import com.homelearning.graphql_playground.lec15.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

    @QueryMapping
    public Flux<CustomerDto> customers(DataFetchingEnvironment environment){
        var callerId = environment.getGraphQlContext().get("caller-id");
        System.out.println("Caller ID" + callerId);
        return this.service.allCustomers();
    }

    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id){
        return this.service.customerById(id)
                .cast(Object.class)
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer){
        return this.service.createCustomer(customer);
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument("customer") CustomerDto dto){
        return this.service.updateCustomer(id, dto);
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id){
        return this.service.deleteCustomer(id);
    }


}
