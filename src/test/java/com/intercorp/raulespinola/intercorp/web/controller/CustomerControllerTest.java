package com.intercorp.raulespinola.intercorp.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.raulespinola.intercorp.exceptions.ResourceNotFoundException;
import com.intercorp.raulespinola.intercorp.models.CustomerDeadDateResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;
import com.intercorp.raulespinola.intercorp.services.CustomerService;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;

import static org.mockito.BDDMockito.given;

@Log4j
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    final static Logger logger = Logger.getLogger(CustomerControllerTest.class);

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    CustomerDto validCustomerA;
    List<CustomerDto> customerList;

    @Before
    public void setUp() {
        LocalDate inputDate = LocalDate.of(1982, 1, 10);
        validCustomerA = CustomerDto.builder()
                .id("abcd")
                .name("Vane")
                .lastName("Jorge")
                .birthDate(inputDate)
                .age(50)
                .build();

        CustomerDto validCustomerB = CustomerDto.builder()
                .id("abcde")
                .name("Jorge")
                .age(25)
                .birthDate(inputDate)
                .lastName("Raulito")
                .build();

        customerList = Arrays.asList(validCustomerA, validCustomerB);
    }

    @Test
    public void getAllCustomersTest() {
        given(customerService.getAllCustomers()).willReturn(customerList);

        try {
            mockMvc.perform(get("/customers/todos").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.[0].id", is(customerList.get(0).getId())));
        } catch (Exception e) {
            logger.error("Something is wrong with thee mockMvc object!", e);
        }
    }

    @Test
    public void newCustomerTest() {
        CustomerDto customerDto = customerList.get(0);
        customerDto.setId(null);
        CustomerDto savedDto = CustomerDto.builder()
                .id("abdc")
                .name("Vane")
                .lastName("Jorge")
                .age(50)
                .build();

        String customerDtoJson = null;
        try {
            customerDtoJson = objectMapper.writeValueAsString(customerDto);
        } catch (JsonProcessingException e) {
            logger.error("Problem with Json Parse", e);
        }
        try {
            given(customerService.saveNewCustomer(any())).willReturn(savedDto);
        } catch (ResourceNotFoundException e) {
            if (logger.isDebugEnabled()) logger.error("Customer not found", e);
        }
        try {
            assert customerDtoJson != null;
            mockMvc.perform(post("/customers/creacliente")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customerDtoJson))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            logger.error("Error with the mock object", e);
        }
    }


    @Test
    public void GetAverageAndDeviationTest() {
        int[] ageList = customerList.stream()
                .mapToInt(CustomerDto::getAge)
                .toArray();
        StadisticalResponse stadisticalMock = new StadisticalResponse(ageList);

        given(customerService.getAverageAndDeviation()).willReturn(stadisticalMock);
        String stadisticalDtoJson = null;
        try {
            stadisticalDtoJson = objectMapper.writeValueAsString(stadisticalMock);
        } catch (JsonProcessingException e) {
            logger.error("Problem with Json Parse", e);
        }

        try {
            mockMvc.perform(get("/customers//kpidclientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(stadisticalDtoJson))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("Error with the mock object", e);
        }
    }

    @Test
    public void testGetAllClientsWithDeadDate() {

        CustomerDeadDateResponse customerDeadDateResponse =
                new CustomerDeadDateResponse(validCustomerA.getName(),
                        validCustomerA.getLastname(),
                        validCustomerA.getAge(),
                        validCustomerA.getBirthdate(),
                        LocalDate.now());

        List<CustomerDeadDateResponse> customerDeadDateResponseListMock = Arrays.asList(customerDeadDateResponse);

        given(customerService.getAllClientsWithDeadDate()).willReturn(customerDeadDateResponseListMock);
        String customerDeadListDtoJson = null;
        try {
            customerDeadListDtoJson = objectMapper.writeValueAsString(customerDeadDateResponse);
        } catch (JsonProcessingException e) {
            logger.error("Problem with Json Parse", e);
        }

        try {
            mockMvc.perform(get("/customers//listclientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customerDeadListDtoJson))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("Error with the mock object", e);
        }

    }
}