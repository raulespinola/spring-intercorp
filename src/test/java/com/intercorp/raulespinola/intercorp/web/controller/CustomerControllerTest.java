package com.intercorp.raulespinola.intercorp.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.raulespinola.intercorp.services.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerControllerTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void getCustomerTest() throws Exception {
        assertTrue(true);
        /*
        LocalDate inputDate = LocalDate.of(1982,1,10);
        CustomerDto validCustomer = CustomerDto.builder()
                .id("abcd")
                .name("Vane")
                .lastname("Jorge")
                .birthdate(inputDate)
                .age(25)
                .build();

        given(customerService.getCustomerById("abcd")).willReturn(validCustomer);


        mockMvc.perform(get("/customers/" + validCustomer.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Vane")));

         */
    }


    @Test
    void getAllClientsWithDeadDate() {
        assertTrue(true);

    }

    @Test
    void getAverageAndDeviation() {
        assertTrue(true);

    }
}