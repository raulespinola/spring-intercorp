package com.intercorp.raulespinola.intercorp.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDeadResponse  extends Customer{
    private LocalDate deadDate;

    public CustomerDeadResponse (CustomerDto c, LocalDate deadDate)
    {
        this.name = c.getName();
        this.age = c.getAge();
        this.lastname = c.getLastname();
        this.birthdate= c.getBirthdate();
        this.setDeadDate(deadDate);
    }
}
