package com.homelearning.graphql_playground.lec07.service;

//import com.homelearning.graphql_playground.sec01.lec03.dto.AgeRangeFilter;
import com.homelearning.graphql_playground.lec07.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Putri", 20, "Jakarta"),
            Customer.create(2, "Budi", 10, "Bandung"),
            Customer.create(3, "Nana", 15, "Solo"),
            Customer.create(4, "Parjo", 5, "Yogyakarta")
    );

    public Flux<Customer> allCustomers(){
        return flux.delayElements(Duration.ofSeconds(1))
                .doOnNext((c-> print("customer " + c.getName())));
    }

    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " + msg);
    }


}
