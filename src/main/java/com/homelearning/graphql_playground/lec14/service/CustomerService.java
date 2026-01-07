package com.homelearning.graphql_playground.lec14.service;

import com.homelearning.graphql_playground.lec14.dto.CustomerDto;
import com.homelearning.graphql_playground.lec14.dto.DeleteResponseDto;
import com.homelearning.graphql_playground.lec14.dto.Status;
import com.homelearning.graphql_playground.lec14.repository.CustomerRepository;
import com.homelearning.graphql_playground.lec14.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Flux<CustomerDto> allCustomers(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> customerById(Integer id){
        return this.repository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> createCustomer(CustomerDto dto){
        return Mono.just(dto)
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<CustomerDto> updateCustomer(Integer id, CustomerDto dto){
        return this.repository.findById(id)
                .map(c -> EntityDtoUtil.toEntity(id, dto))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<DeleteResponseDto> deleteCustomer(Integer id){
        return this.repository.deleteById(id)
                .thenReturn(DeleteResponseDto.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDto.create(id, Status.FAILURE));
    }
}
