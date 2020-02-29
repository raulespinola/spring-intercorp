package com.intercorp.raulespinola.intercorp.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDeadResponse  extends Customer{
    private LocalDate deadDate;

    @Builder
    public CustomerDeadResponse (String name, String lastName, int age, LocalDate birthDate, LocalDate deadDate)
    {
        this.name = name;
        this.age = age;
        this.lastname = lastName;
        this.birthdate= birthDate;
        this.deadDate = deadDate;
    }
}
