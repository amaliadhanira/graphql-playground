package com.homelearning.graphql_playground.lec04;

import com.homelearning.graphql_playground.lec04.dto.Customer;
import com.homelearning.graphql_playground.lec04.dto.CustomerOrderDto;
import com.homelearning.graphql_playground.lec04.service.CustomerService;
import com.homelearning.graphql_playground.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    //@QueryMapping
    @SchemaMapping(typeName = "Query")
    public Flux<Customer> customers(){
        return this.customerService.allCustomers();
    }

//    @BatchMapping(typeName = "Customer")
//    public Flux<List<CustomerOrderDto>> orders(List<Customer> customer){
//        System.out.println("Orders method invoked for " + customer);
//        return this.orderService.ordersByCustomerName(
//                customer.stream().map(Customer::getName).collect(Collectors.toList())
//        );
//    }

    @BatchMapping(typeName = "Customer")
    public Mono<Map<Customer, List<CustomerOrderDto>>> orders(List<Customer> customer){
        System.out.println("Orders method invoked for " + customer);
        return this.orderService.fetchOrdersMap(customer);
    }

    @SchemaMapping(typeName = "Customer")
    public Mono<Integer> age(){
        return Mono.just(100);
    }

}

