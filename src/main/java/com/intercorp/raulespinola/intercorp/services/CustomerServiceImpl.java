package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.models.CustomerDeadDateResponse;
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
        return new StadisticalResponse(customerRepository.findAll());
    }

    @Override
    public List<CustomerDeadDateResponse>  getAllClientsWithDeadDate() {
        LocalDate today = LocalDate.now();
        Random rand = new Random();

        return customerRepository
                .findAll()
                .stream()
                .map(p -> new CustomerDeadDateResponse(p.getName(),
                        p.getLastname(),p.getAge(),p.getBirthdate(),
                        today.plusDays(rand.nextInt(3650))))
                .collect(Collectors.toList());
    }

}
