package com.intercorp.raulespinola.intercorp.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document (collection = "Customers")
public class CustomerEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    @NotBlank(message= "Name cannot be blank")
    private String name;

    @NotNull
    @NotBlank(message= "Lastname canoot be blank")
    private String lastname;

    @Past
    private LocalDate birthdate;

    @Min(value=18, message="Age cannot be less than 18")
    @Max(value=100, message="Age cannot be more than 100")
    @NotNull
    @NotBlank
    private int age;

}
