package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.models.CustomerDeadResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        customerRepository.save(customerDto);
        return customerDto;
    }

    @Override
    public StadisticalResponse getAverageAndDeviation() {
        List<CustomerDto> customerDtoList = this.customerRepository.findAll();

        double mean = customerDtoList.stream()
                .mapToInt(CustomerDto::getAge)
                .average()
                .orElse(0.0);

        double variance = customerDtoList.stream()
                .map(i -> {
                    return (i.getAge() - mean);
                })
                .map(i -> i*i)
                .mapToDouble(i -> i)
                .average()
                .orElse(0.0);

        //Standard Deviation
        double standardDeviation = Math.sqrt(variance);

        double average = customerDtoList.stream()
                .mapToInt(CustomerDto::getAge)
                .summaryStatistics()
                .getAverage();

        return StadisticalResponse.builder()
                .average(average)
                .variance(variance)
                .standardDeviation(standardDeviation)
                .build();
    }

    @Override
    public List<CustomerDeadResponse>  getAllClientsWithDeadDate() {
        LocalDate today = LocalDate.now();
        Random rand = new Random();

        return customerRepository
                .findAll()
                .stream()
                .map(p -> new CustomerDeadResponse(p, today.plusDays(rand.nextInt(3650))))
                .collect(Collectors.toList());
    }

}
