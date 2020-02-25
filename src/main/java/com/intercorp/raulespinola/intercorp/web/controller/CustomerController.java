package com.intercorp.raulespinola.intercorp.web.controller;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    // Find Customer by Id
    @GetMapping("/{id}")
    public Optional<Customer> getById(@PathVariable("id") String id){
        return this.customerRepository.findById(id);
    }

    // List all clients
    @GetMapping("/listclientes")
    public List<Customer> getAllClientsWithDeadDate(){

        return this.customerRepository.findAll();
    }

    // List all clients
    @GetMapping("/kpidclientes")
    public List<Double> getAverageAndDeviation(){

        List<Customer> customerList = this.customerRepository.findAll();
                // mean
        double mean = customerList.stream()
                .mapToInt(Customer::getAge)
                .average()
                .getAsDouble();

        System.out.println("Medio" + mean );


        // Variance
        double variance = customerList.stream()
                .map(i -> {
                    return (i.getAge() - mean);
                })
                .map(i -> i*i)
                .mapToDouble(i -> i).average().getAsDouble();

        System.out.println("Varianza" + variance);

        //Standard Deviation
        double standardDeviation = Math.sqrt(variance);
        System.out.println("Desviacion" + standardDeviation);


        double average = customerList.stream()
                .mapToInt(Customer::getAge)
                .summaryStatistics()
                .getAverage();

        List <Double> out = Arrays.asList(average, variance, standardDeviation);

        return out;
    }
}












