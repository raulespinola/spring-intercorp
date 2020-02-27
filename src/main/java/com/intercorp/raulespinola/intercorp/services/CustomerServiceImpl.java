package com.intercorp.raulespinola.intercorp.services;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerById(String customerId) throws ResourceNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Is not found"));
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        customerRepository.save(customerDto);
        return customerDto;
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        customerRepository.save(customerDto);
    }

    @Override
    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Double> getAverageAndDeviation() {
        List<CustomerDto> customerDtoList = this.customerRepository.findAll();

        double mean = customerDtoList.stream()
                .mapToInt(CustomerDto::getAge)
                .average()
                .getAsDouble();

        double variance = customerDtoList.stream()
                .map(i -> {
                    return (i.getAge() - mean);
                })
                .map(i -> i*i)
                .mapToDouble(i -> i).average().getAsDouble();

        //Standard Deviation
        double standardDeviation = Math.sqrt(variance);

        double average = customerDtoList.stream()
                .mapToInt(CustomerDto::getAge)
                .summaryStatistics()
                .getAverage();

        return Arrays.asList(average, variance, standardDeviation);
    }

    @Override
    public List<CustomerDto> getAllClientsWithDeadDate() {
        return customerRepository.findAll();
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public String getHello() {
        return "Hello JUnit 5";
    }
}
