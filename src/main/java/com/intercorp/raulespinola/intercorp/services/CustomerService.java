package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.models.CustomerDeadResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;

import java.util.List;

public interface CustomerService {

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    StadisticalResponse getAverageAndDeviation();

    List<CustomerDeadResponse> getAllClientsWithDeadDate();

    List<CustomerDto> getAllCustomers();
}
