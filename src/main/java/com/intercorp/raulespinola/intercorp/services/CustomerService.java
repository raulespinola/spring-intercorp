package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.domain.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(String customerId);

    Customer saveNewCustomer(Customer customer);

    void updateCustomer(String customerId, Customer customer);

    void deleteById(String customerId);
}
