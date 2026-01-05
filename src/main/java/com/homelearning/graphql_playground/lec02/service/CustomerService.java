package com.homelearning.graphql_playground.lec02.service;

import com.homelearning.graphql_playground.lec02.dto.AgeRangeFilter;
import com.homelearning.graphql_playground.lec02.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Putri", 20, "Jakarta"),
            Customer.create(2, "Budi", 10, "Bandung"),
            Customer.create(3, "Nana", 15, "Solo"),
            Customer.create(4, "Parjo", 5, "Yogyakarta")
    );

    public Flux<Customer> allCustomers(){
        return flux;
    }

    public Mono<Customer> customerById(Integer id){
        return flux.filter(c -> c.getId().equals(id))
                .next();
    }

    public Flux<Customer> nameContains(String name){
        return flux
                .filter(c -> c.getName().contains(name));
    }

    public Flux<Customer> withinAgeRange(AgeRangeFilter filter) {
        return flux
                .filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
    }
}
