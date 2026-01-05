package com.homelearning.graphql_playground.lec06;

import com.homelearning.graphql_playground.lec06.dto.Customer;
import com.homelearning.graphql_playground.lec06.dto.CustomerOrderDto;
import com.homelearning.graphql_playground.lec06.dto.CustomerWithOrder;
import com.homelearning.graphql_playground.lec06.service.CustomerOrderDataFetcher;
import com.homelearning.graphql_playground.lec06.service.CustomerService;
import com.homelearning.graphql_playground.lec06.service.OrderService;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerOrderDataFetcher customerOrderDataFetcher;

//    @Autowired
//    private OrderService orderService;

    //@QueryMapping
    @SchemaMapping(typeName = "Query")
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet){
        return this.customerOrderDataFetcher.customerOrders(selectionSet);
    }

//    @SchemaMapping(typeName = "Customer")
//    public Flux<CustomerOrderDto> orders(Customer customer){
//        System.out.println("Orders method invoked for " + customer.getName());
//        return this.orderService.ordersByCustomerName(customer.getName());
//    }

}

