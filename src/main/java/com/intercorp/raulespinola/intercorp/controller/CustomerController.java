package com.intercorp.raulespinola.intercorp.controller;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    public List<Customer> getAll(){
       return this.customerRepository.findAll();
    }
}
