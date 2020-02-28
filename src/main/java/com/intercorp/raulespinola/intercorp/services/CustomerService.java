package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.document.CustomerEntity;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerEntity getCustomerById(String customerId) throws ResourceNotFoundException;

    CustomerEntity saveNewCustomer(CustomerEntity customerEntity);

    void updateCustomer(CustomerEntity customerEntity);

    void deleteById(String customerId);

    List<Double> getAverageAndDeviation();

    Map<String, LocalDate> getAllClientsWithDeadDate();

    List<CustomerEntity> findAll();

    String getHello();
}
