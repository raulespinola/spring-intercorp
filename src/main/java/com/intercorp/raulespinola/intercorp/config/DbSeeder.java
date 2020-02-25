package com.intercorp.raulespinola.intercorp.config;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DbSeeder implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public DbSeeder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       CustomerDto c1=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Raul")
                .lastname("Espinola")
                .age(37)
                .build();

        CustomerDto c2=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(25)
                .build();

        CustomerDto c3=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(51)
                .build();

        CustomerDto c4=  CustomerDto.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(18)
                .build();

        // Save all Customers
        this.customerRepository.save(c1);
        this.customerRepository.save(c2);
        this.customerRepository.save(c3);
        this.customerRepository.save(c4);

        System.out.println("Customers Loaded " + customerRepository.count());
    }
}
