package com.intercorp.raulespinola.intercorp.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public abstract class Customer {
    @NotNull
    @NotBlank(message= "Name cannot be blank")
    protected String name;

    @NotNull
    @NotBlank(message= "Lastname canoot be blank")
    protected String lastname;

    @Past
    protected LocalDate birthdate;

    @Min(value=18, message="Age cannot be less than 18")
    @Max(value=100, message="Age cannot be more than 100")
    @NotNull
    @NotBlank
    protected int age;
}
