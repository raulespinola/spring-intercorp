package com.intercorp.raulespinola.intercorp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StadisticalResponse {
    private double average;
    private double variance;
    private double standardDeviation;
}
