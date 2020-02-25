package com.intercorp.raulespinola.intercorp.web.controller;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import com.intercorp.raulespinola.intercorp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping({"/{customerId}"})
    public ResponseEntity<Optional<CustomerDto>> getCustomer(@NotNull @PathVariable String customerId) {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @NotNull @RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.saveNewCustomer(customerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/customer" + savedCustomerDto.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable String customerId) {
        customerService.deleteById(customerId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    // List all clients
    @GetMapping("/listclientes")
    public List<CustomerDto> getAllClientsWithDeadDate(){
        return this.customerService.getAllClientsWithDeadDate();
    }

    // List Average and Standart Deviation
    @GetMapping("/kpidclientes")
    public List<Double> getAverageAndDeviation(){
        return this.customerService.getAverageAndDeviation();
    }
}












