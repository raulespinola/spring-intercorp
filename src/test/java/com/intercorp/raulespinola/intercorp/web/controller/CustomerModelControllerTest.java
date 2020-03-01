package com.intercorp.raulespinola.intercorp.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.raulespinola.intercorp.models.CustomerDeadDateResponse;
import com.intercorp.raulespinola.intercorp.models.CustomerDto;
import com.intercorp.raulespinola.intercorp.models.StadisticalResponse;
import com.intercorp.raulespinola.intercorp.services.CustomerService;

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

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerModelControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomerA;
    List<CustomerDto> customerList;


    @Before
    public void setUp(){
        LocalDate inputDate = LocalDate.of(1982,1,10);
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
    public void getAllCustomersTest() throws Exception {
        given(customerService.getAllCustomers()).willReturn(customerList);

        mockMvc.perform(get("/customers/todos").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", is(customerList.get(0).getId())));
    }

    @Test
    public void newCustomerTest() throws Exception {
        CustomerDto customerDto = customerList.get(0);
        customerDto.setId(null);
        CustomerDto savedDto = CustomerDto.builder()
                .id("abdc")
                .name("Vane")
                .lastName("Jorge")
                .age(50)
                .build();

        String customerDtoJson = objectMapper.writeValueAsString(customerDto);
        given(customerService.saveNewCustomer(any())).willReturn(savedDto);
        mockMvc.perform(post("/customers/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated());
    }


    @Test
    public void GetAverageAndDeviationTest() throws Exception {
        int[] ageList = customerList.stream()
                .mapToInt(CustomerDto::getAge)
                .toArray();
        StadisticalResponse stadisticalMock = new StadisticalResponse(ageList);

        given(customerService.getAverageAndDeviation()).willReturn(stadisticalMock);
        String staditicalDtoJson = objectMapper.writeValueAsString(stadisticalMock);

        mockMvc.perform(get("/customers//kpidclientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(staditicalDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllClientsWithDeadDate() throws Exception {

        CustomerDeadDateResponse customerDeadDateResponse =
                new CustomerDeadDateResponse(validCustomerA.getName(),
                        validCustomerA.getLastname(),
                        validCustomerA.getAge(),
                        validCustomerA.getBirthdate(),
                        LocalDate.now());

        List<CustomerDeadDateResponse> customerDeadDateResponseListMock =  Arrays.asList(customerDeadDateResponse);

        given(customerService.getAllClientsWithDeadDate()).willReturn(customerDeadDateResponseListMock);
        String customerDeadListDtoJson = objectMapper.writeValueAsString(customerDeadDateResponse);

        mockMvc.perform(get("/customers//listclientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDeadListDtoJson))
                .andExpect(status().isOk());

    }
}