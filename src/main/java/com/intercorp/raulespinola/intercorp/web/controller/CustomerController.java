package com.intercorp.raulespinola.intercorp.web.controller;

import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
import com.intercorp.raulespinola.intercorp.models.CustomerDeadDateResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;
import com.intercorp.raulespinola.intercorp.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    final static Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "View a list of available customers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/todos")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping("/creacliente")
    public ResponseEntity newCustomer(@Valid @NotNull @RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = null;
        try {
            savedCustomerDto = customerService.saveNewCustomer(customerDto);
        } catch (ResourceNotFoundException e) {
            if (logger.isDebugEnabled()) logger.error("Customer Not Found", e);
        }
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/customer" + savedCustomerDto.getId());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Average Age and Standart Deviation Age")
    @GetMapping("/kpidclientes")
    public ResponseEntity<StadisticalResponse> getAverageAndDeviation() {
        return new ResponseEntity<StadisticalResponse>(customerService
                .getAverageAndDeviation(), HttpStatus.OK);
    }

    @ApiOperation(value = "List of all Clients with their Dead Dates")
    @GetMapping("/listclientes")
    public ResponseEntity<List<CustomerDeadDateResponse>> getAllClientsWithDeadDate() {
        return new ResponseEntity<List<CustomerDeadDateResponse>>(customerService
                .getAllClientsWithDeadDate(), HttpStatus.OK);
    }
}












