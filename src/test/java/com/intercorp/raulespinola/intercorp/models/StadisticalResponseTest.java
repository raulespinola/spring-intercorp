package com.intercorp.raulespinola.intercorp.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@WebMvcTest(StadisticalResponse.class)
public class StadisticalResponseTest {

    StadisticalResponse stadisticalResponseA, stadisticalResponseB;
    List<CustomerDto> customerList;
    int[] data;

    @Before
    public void setUp() {
        data = new int[]{50, 35, 65};
        CustomerDto validCustomerA = CustomerDto.builder()
                .id("abcd")
                .name("Vane")
                .lastName("Jorge")
                .age(50)
                .build();

        CustomerDto validCustomerB = CustomerDto.builder()
                .id("abcde")
                .name("Jorge")
                .age(35)
                .lastName("Raulito")
                .build();

        CustomerDto validCustomerC = CustomerDto.builder()
                .id("abcde")
                .name("Jorge")
                .age(65)
                .lastName("Raulito")
                .build();

        customerList = Arrays.asList(validCustomerA, validCustomerB, validCustomerC);
        stadisticalResponseB = new StadisticalResponse(customerList);
        stadisticalResponseA = new StadisticalResponse(data);
    }

    @Test
    public void calculateAverageTest() {
        assertEquals(stadisticalResponseB.getAverage(),
                stadisticalResponseA.getAverage());
    }

    @Test
    public void calculateVarianceTest() {
        assertEquals(stadisticalResponseB.getVariance(),
                stadisticalResponseA.getVariance());
    }

    @Test
    public void calculateStandardDeviationTest() {
        assertEquals(stadisticalResponseB.getStandardDeviation(),
                stadisticalResponseA.getStandardDeviation());
    }
}