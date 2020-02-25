package com.intercorp.raulespinola.intercorp.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document (collection = "Customers")
public class CustomerDto implements Serializable {

    @Id
    private String id;
    private String name;
    private String lastname;
    private int age;
    private LocalDate birthdate;
}
