package com.homelearning.graphql_playground.lec05;

import com.homelearning.graphql_playground.lec05.dto.Customer;
import com.homelearning.graphql_playground.lec05.dto.Account;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @SchemaMapping
    public Mono<Account> account(Customer customer, DataFetchingEnvironment environment){
        System.out.println("account: " + environment.getDocument());
        var type = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";

        return Mono.just(
                Account.create(
                        UUID.randomUUID(),
                        ThreadLocalRandom.current().nextInt(1, 1000),
                        type
                )
        );
    }
}
