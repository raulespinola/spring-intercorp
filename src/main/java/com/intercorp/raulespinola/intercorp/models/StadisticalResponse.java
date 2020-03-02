package com.intercorp.raulespinola.intercorp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StadisticalResponse {
    private double average;
    private double variance;
    private double standardDeviation;

    private double calculateAverage(int[] data) {
        // The mean average
        double mean = 0.0;
        for (int i = 0; i < data.length; i++) {
            mean += data[i];
        }
        mean /= data.length;
        return mean;
    }

    private double calculateAverage(List<CustomerDto> customerDtoList) {
        return customerDtoList.stream()
                .mapToInt(CustomerDto::getAge)
                .average()
                .orElse(0.0);
    }

    private double calculateVariance(List<CustomerDto> customerDtoList, double average) {
        return customerDtoList.stream()
                .map(i -> {
                    return (i.getAge() - average);
                })
                .map(i -> i * i)
                .mapToDouble(i -> i)
                .average()
                .orElse(0.0);
    }

    private double calculateVariance(int[] data, double mean) {
        // The variance
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            variance += (data[i] - mean) * (data[i] - mean);
        }
        variance /= data.length;
        return variance;
    }

    public double calculateStandardDeviation(double variance) {
        return Math.sqrt(variance);
    }

    public StadisticalResponse(int[] data) {
        this.average = calculateAverage(data);
        this.variance = calculateVariance(data, this.average);
        this.standardDeviation = calculateStandardDeviation(variance);
    }

    public StadisticalResponse(List<CustomerDto> customerDtoList) {
        this.average = calculateAverage(customerDtoList);
        this.variance = calculateVariance(customerDtoList, this.average);
        this.standardDeviation = calculateStandardDeviation(variance);
    }

}
