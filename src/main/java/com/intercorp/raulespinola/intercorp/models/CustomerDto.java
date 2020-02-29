package com.intercorp.raulespinola.intercorp.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document (collection = "Customers")
public class CustomerDto extends Customer implements Serializable {
    @Id
    private String id;
}
