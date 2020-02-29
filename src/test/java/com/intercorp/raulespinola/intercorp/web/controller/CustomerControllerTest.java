package com.intercorp.raulespinola.intercorp.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.raulespinola.intercorp.models.Customer;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import com.intercorp.raulespinola.intercorp.services.CustomerService;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<CustomerDto> customerList;

    @Test
    public void getAllCustomersTest() throws Exception {

        LocalDate inputDate = LocalDate.of(1982,1,10);
        CustomerDto validCustomerA = CustomerDto.builder()
                .id("abcd")
                .name("Vane")
                .lastName("Jorge")
                .birthDate(inputDate)
                .age(50)
                .build();

        CustomerDto validCustomerB = CustomerDto.builder()
                .id("abcd")
                .name("Vane")
                .age(25)
                .birthDate(inputDate)
                .lastName("Raulito")
                .build();


        customerList = Arrays.asList(validCustomerA, validCustomerB);
        given(customerService.getAllCustomers()).willReturn(customerList);

        mockMvc.perform(get("/customers/todos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", is(customerList.get(0).getId())));
    }

    //@Test
    void getAllCustomers() {
    }

    //@Test
    void newCustomer() {
    }

    //@Test
    void testGetAverageAndDeviation() {
    }

    //@Test
    void testGetAllClientsWithDeadDate() {
    }
}