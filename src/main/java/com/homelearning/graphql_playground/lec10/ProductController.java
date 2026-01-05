package com.homelearning.graphql_playground.lec10;

import com.homelearning.graphql_playground.lec10.dto.Book;
import com.homelearning.graphql_playground.lec10.dto.Electronics;
import com.homelearning.graphql_playground.lec10.dto.FruitDto;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products(){
        return Flux.just(
                FruitDto.create("banana", 1, LocalDate.now().plusDays(3)),
                FruitDto.create("apple", 2, LocalDate.now().plusDays(5)),
                Electronics.create("Mac Book", 600, "APPLE"),
                Electronics.create("Galaxy Tab", 600, "SAMSUNG"),
                Book.create("Sebuah Seni untuk Bersikap Bodo Amat", 40, "Mark Manson")
        );
    }
}
