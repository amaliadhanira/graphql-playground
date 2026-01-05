package com.homelearning.graphql_playground.lec07.service;

import com.homelearning.graphql_playground.lec07.dto.CustomerOrderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    private final Map<String, List<CustomerOrderDto>> map = Map.of(
            "Budi", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "calculus"),
                    CustomerOrderDto.create(UUID.randomUUID(), "science book")
            )
//            "Putri", List.of(
//                    CustomerOrderDto.create(UUID.randomUUID(), "Notebook"),
//                    CustomerOrderDto.create(UUID.randomUUID(), "math book"),
//                    CustomerOrderDto.create(UUID.randomUUID(), "dictionary")
//            )
    );

    public Flux<CustomerOrderDto> ordersByCustomerName(String name){
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(o -> print("order for " + name));
    }

    private void print(String msg){
        System.out.println(LocalDateTime.now() + " : " + msg);
    }
}
