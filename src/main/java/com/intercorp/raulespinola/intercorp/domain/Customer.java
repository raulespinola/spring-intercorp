package com.intercorp.raulespinola.intercorp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document (collection = "Customers")
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String lastname;
    private int age;
    private LocalDate birthdate;
}
