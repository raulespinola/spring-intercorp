package com.intercorp.raulespinola.intercorp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.raulespinola.intercorp.domain.Customer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Customer validCustomer;

    @Before
    public void setUp() {
        validCustomer = Customer.builder()
                .id("5e5313426a0ac4352cecd763")
                .name("Jorge")
                .lastname("Espinola")
                .age(25)
                .birthdate(LocalDate.of(1982, 10, 27))
                .build();
    }


    @Test
    void getAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }



    @Test
    void getAllClientsWithDeadDate() {
    }

    @Test
    void getAverageAndDeviation() {
    }

}
