package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.document.CustomerEntity;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
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
    public CustomerEntity getCustomerById(String customerId) throws ResourceNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Is not found"));
    }

    @Override
    public CustomerEntity saveNewCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    @Override
    public void updateCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Double> getAverageAndDeviation() {
        List<CustomerEntity> customerEntityList = this.customerRepository.findAll();

        double mean = customerEntityList.stream()
                .mapToInt(CustomerEntity::getAge)
                .average()
                .getAsDouble();

        double variance = customerEntityList.stream()
                .map(i -> {
                    return (i.getAge() - mean);
                })
                .map(i -> i*i)
                .mapToDouble(i -> i).average().getAsDouble();

        //Standard Deviation
        double standardDeviation = Math.sqrt(variance);

        double average = customerEntityList.stream()
                .mapToInt(CustomerEntity::getAge)
                .summaryStatistics()
                .getAverage();

        return Arrays.asList(average, variance, standardDeviation);
    }

    @Override
    public Map<String, LocalDate>  getAllClientsWithDeadDate() {

        LocalDate today = LocalDate.now();
        Random rand = new Random();
        return customerRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(CustomerEntity::toString, p -> p.getBirthdate().plusYears(p.getAge()).plusDays(rand.nextInt(3560))));
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public String getHello() {
        return "Hello JUnit 5";
    }
}
