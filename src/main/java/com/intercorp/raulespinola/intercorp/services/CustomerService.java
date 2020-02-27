package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDto getCustomerById(String customerId) throws ResourceNotFoundException;

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(CustomerDto customerDto);

    void deleteById(String customerId);

    List<Double> getAverageAndDeviation();

    List<CustomerDto> getAllClientsWithDeadDate();

    List<CustomerDto> findAll();

    String getHello();
}
