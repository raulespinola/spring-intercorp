package com.intercorp.raulespinola.intercorp.web.controller;

import com.intercorp.raulespinola.intercorp.document.CustomerEntity;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<CustomerEntity>> getAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get a customer by Id")
    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerEntity> getCustomer(
            @ApiParam(value = "Customer id from which customer object will retrieve", required = true) @PathVariable(value = "id") String customerId) throws ResourceNotFoundException {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
    @ApiOperation(value = "Add an employee")
    @PostMapping
    public ResponseEntity newCustomer(@Valid @NotNull @RequestBody CustomerEntity customerEntity) {
        CustomerEntity savedCustomerEntity = customerService.saveNewCustomer(customerEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/customer" + savedCustomerEntity.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Update an employee")
    @PutMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer( @ApiParam(value = "Employee Id to update customer object", required = true) @PathVariable(value = "id") String customerId,
                              @ApiParam(value = "Update customer object", required = true) @Valid @RequestBody CustomerEntity customerEntity) {
        customerService.updateCustomer(customerEntity);
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
    public Map<String, LocalDate> getAllClientsWithDeadDate(){
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












