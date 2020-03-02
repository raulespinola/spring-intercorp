package com.intercorp.raulespinola.intercorp.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDeadDateResponse extends CustomerModel {
    private LocalDate deadDate;

    @Builder
    public CustomerDeadDateResponse(String name, String lastName, int age, LocalDate birthDate, LocalDate deadDate) {
        this.name = name;
        this.age = age;
        this.lastname = lastName;
        this.birthdate = birthDate;
        this.deadDate = deadDate;
    }
}
