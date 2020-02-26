package com.intercorp.raulespinola.intercorp.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document (collection = "Customers")
public class CustomerDto implements Serializable {

    @Id
    private String id;

    @NotNull
    @NotBlank(message= "Name cannot be blank")
    private String name;

    @NotNull
    @NotBlank(message= "Lastname canoot be blank")
    private String lastname;

    @Min(value=18, message="Customer cannot have less than 18")
    @Max(value=90, message="Customer cannot have more than 90")
    private int age;

    @Past
    private LocalDate birthdate;
}
