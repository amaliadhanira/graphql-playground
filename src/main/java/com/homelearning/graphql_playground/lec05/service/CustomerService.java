package com.homelearning.graphql_playground.lec05.service;

//import com.homelearning.graphql_playground.sec01.lec03.dto.AgeRangeFilter;
import com.homelearning.graphql_playground.lec05.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "sam", 20, "atlanta"),
            Customer.create(2, "jake", 10, "las vegas"),
            Customer.create(3, "mike", 15, "miami"),
            Customer.create(4, "john", 5, "houston")
    );

    public Flux<Customer> allCustomers(){
        return flux;
    }

}
