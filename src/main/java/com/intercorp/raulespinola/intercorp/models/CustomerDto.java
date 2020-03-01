package com.intercorp.raulespinola.intercorp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Document (collection = "Customers")
public class CustomerDto extends CustomerModel implements Serializable {
    @Id
    private String id;

    @Builder
    public CustomerDto(String id, String name, String lastName, int age, LocalDate birthDate)
    {
        this.id=id;
        this.name = name;
        this.age = age;
        this.lastname = lastName;
        this.birthdate= birthDate;
    }
}
