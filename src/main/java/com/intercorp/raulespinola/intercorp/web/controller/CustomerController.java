package com.intercorp.raulespinola.intercorp.web.controller;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import com.intercorp.raulespinola.intercorp.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "View a list of available customers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get a customer by Id")
    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> getCustomer(
            @ApiParam(value = "Customer id from which customer object will retrieve", required = true) @PathVariable(value = "id") String customerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
    @ApiOperation(value = "Add an employee")
    @PostMapping
    public ResponseEntity handlePost(@Valid @NotNull @RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.saveNewCustomer(customerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/customer" + savedCustomerDto.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Update an employee")
    @PutMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate( @ApiParam(value = "Employee Id to update customer object", required = true) @PathVariable(value = "id") String customerId,
                              @ApiParam(value = "Update customer object", required = true) @Valid @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
    }
    @ApiOperation(value = "Delete a Customer")
    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@ApiParam(value = "Customer Id from which customer object will delete from database table",
            required = true) @PathVariable(value = "id") String customerId) {
        customerService.deleteById(customerId);
    }
    // List all clients
    @ApiOperation(value = "List of all Clients with their Dead Dates")
    @GetMapping("/listclientes")
    public List<CustomerDto> getAllClientsWithDeadDate(){
        return this.customerService.getAllClientsWithDeadDate();
    }
    // List Average and Standart Deviation
    @ApiOperation(value = "Average Age and Standart Deviation Age")
    @GetMapping("/kpidclientes")
    public List<Double> getAverageAndDeviation(){
        return this.customerService.getAverageAndDeviation();
    }

    @GetMapping("/hello")
    public String getHello() {
        return customerService.getHello();
    }
}












