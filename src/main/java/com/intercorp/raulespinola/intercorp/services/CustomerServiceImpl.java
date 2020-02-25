package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer getCustomerById(String customerId) {
        return null;
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        return null;
    }

    @Override
    public void updateCustomer(String customerId, Customer customer) {

    }

    @Override
    public void deleteById(String customerId) {

    }
}
