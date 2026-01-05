package com.homelearning.graphql_playground.lec05;

import com.homelearning.graphql_playground.lec05.dto.Customer;
import com.homelearning.graphql_playground.lec05.service.CustomerService;
import com.homelearning.graphql_playground.lec05.service.OrderService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @QueryMapping
    public Flux<Customer> customers(DataFetchingEnvironment environment){
        System.out.println("customer: " + environment.getDocument());
        System.out.println(environment.getOperationDefinition());
        return this.customerService.allCustomers();
    }

}

