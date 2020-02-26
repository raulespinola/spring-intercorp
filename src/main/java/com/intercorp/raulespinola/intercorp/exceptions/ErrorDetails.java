package com.intercorp.raulespinola.intercorp.exceptions;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;
}
