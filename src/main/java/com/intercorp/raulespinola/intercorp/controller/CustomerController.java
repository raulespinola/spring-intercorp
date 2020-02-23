package com.intercorp.raulespinola.intercorp.controller;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //List All customers
    @GetMapping("/all")
    public List<Customer> getAll(){
       return this.customerRepository.findAll();
    }

    //Insert new Customer
    @PutMapping
    public void insert(@RequestBody Customer customer){
        this.customerRepository.insert(customer);
    }

    //Add new Customer
    @PostMapping
    public void update(@RequestBody Customer customer){
        this.customerRepository.save(customer);
    }

    // Delete Customer by Id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.customerRepository.deleteById(id);
    }
}
