package com.homelearning.graphql_playground.lec05.service;

import com.homelearning.graphql_playground.lec05.dto.Customer;
import com.homelearning.graphql_playground.lec05.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrderDto>> map = Map.of(
            "Budi", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "calculus"),
                    CustomerOrderDto.create(UUID.randomUUID(), "science book")
            ),
            "Putri", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "Notebook"),
                    CustomerOrderDto.create(UUID.randomUUID(), "math book"),
                    CustomerOrderDto.create(UUID.randomUUID(), "dictionary")
            )
    );

    public Flux<CustomerOrderDto> ordersByCustomerName(String name){
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }

    public Flux<List<CustomerOrderDto>> ordersByCustomerName(List<String> names){
        return Flux.fromIterable(names)
                .flatMapSequential(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
    }

    private Mono<List<CustomerOrderDto>> fetchOrders(String name){
        return Mono.justOrEmpty(map.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 500)));
    }

    public Mono<Map<Customer, List<CustomerOrderDto>>> fetchOrdersMap(List<Customer> customers){
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(
                        Tuple2::getT1,
                        Tuple2::getT2
                );
    }
}
