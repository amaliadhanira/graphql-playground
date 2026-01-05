package com.homelearning.graphql_playground.lec07.service;

import com.homelearning.graphql_playground.lec07.dto.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@Service
public class CustomerOrderDataFetcher implements DataFetcher<Flux<CustomerWithOrder>> {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

//    public Flux<CustomerWithOrder> customerOrders(DataFetchingFieldSelectionSet selectionSet){
//
//    }

    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders){
        return includeOrders ? f -> f.flatMapSequential(this::fetchOrders) : f-> f;
    }

    private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customerWithOrder){
        return this.orderService.ordersByCustomerName(customerWithOrder.getName())
                .collectList()
                .doOnNext(customerWithOrder::setOrders)
                .thenReturn(customerWithOrder);
    }

    @Override
    public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) throws Exception {
        var includeOrders = environment.getSelectionSet().contains("orders");
        System.out.println(includeOrders);
        return this.customerService.allCustomers()
                .map(c -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(), Collections.emptyList()))
                .transform(this.updateOrdersTransformer(includeOrders));
    }
}
