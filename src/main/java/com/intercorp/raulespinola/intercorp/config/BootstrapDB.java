package com.intercorp.raulespinola.intercorp.config;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import org.springframework.boot.CommandLineRunner;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;

import java.time.LocalDate;

public class BootstrapDB implements CommandLineRunner {
    private CustomerRepository customerRepository;

    public BootstrapDB(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        CustomerDto c1=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Raul")
                .lastname("Espinola")
                .age(61)
                .build();

        CustomerDto c2=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(37)
                .build();

        CustomerDto c3=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(41)
                .build();


        // Save all Customers
        this.customerRepository.save(c1);
        this.customerRepository.save(c2);
        this.customerRepository.save(c3);

        System.out.println("Customers Loaded " + customerRepository.count());
    }
}
