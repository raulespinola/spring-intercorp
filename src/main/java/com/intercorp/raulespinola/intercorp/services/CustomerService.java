package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
import com.intercorp.raulespinola.intercorp.models.CustomerDeadDateResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;

import java.util.List;

public interface CustomerService {

    CustomerDto saveNewCustomer(CustomerDto customerDto) throws ResourceNotFoundException;

    StadisticalResponse getAverageAndDeviation();

    List<CustomerDeadDateResponse> getAllClientsWithDeadDate();

    List<CustomerDto> getAllCustomers();
}
