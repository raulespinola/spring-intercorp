package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(String customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(CustomerDto customerDto);

    void deleteById(String customerId);

    List<Double> getAverageAndDeviation();

    List<CustomerDto> getAllClientsWithDeadDate();

    List<CustomerDto> findAll();
}
