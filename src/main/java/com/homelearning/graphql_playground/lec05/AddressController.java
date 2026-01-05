package com.homelearning.graphql_playground.lec05;

import com.homelearning.graphql_playground.lec05.dto.Customer;
import com.homelearning.graphql_playground.lec05.dto.Address;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping
    public Mono<Address> address(Customer customer, DataFetchingEnvironment environment){
        System.out.println("address: " + environment.getDocument());
        return Mono.just(
                Address.create(customer.getName() + " street", customer.getName() + " city")
        );
    }
}
