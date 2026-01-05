package com.homelearning.graphql_playground.lec09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;
import graphql.scalars.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AllTypes {
    private UUID id;
    private Integer height;
    private Float temperature;
    private String city;
    private Boolean isValid;

    private Long distance;
    private Byte ageInYears;
    private Short ageInMonths;
    private BigDecimal bigDecimal;
    private BigInteger bigInteger;
    private LocalDate date;
    private LocalTime time;
    private OffsetDateTime dateTime;
    private Car car;


}

/*
    type AllType{
    id: ID
    height: Int
    temperature: Float
    city: String
    isValid: Boolean
    distance: Long
    ageInYears: Byte
    ageInMonths: Short
    bigDecimal: BigDecimal
    bigInteger: BigInteger
    date: Date
    time: LocalTime
    dateTime: DateTime
    car: Car
}
 */
