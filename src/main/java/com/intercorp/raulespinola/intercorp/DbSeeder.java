package com.intercorp.raulespinola.intercorp;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import com.intercorp.raulespinola.intercorp.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DbSeeder implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public DbSeeder(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       Customer c1=  Customer.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Raul")
                .lastname("Espinola")
                .age(37)
                .build();

        Customer c2=  Customer.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(25)
                .build();

        Customer c3=  Customer.builder()
                .birthdate(LocalDate.of(1982, 10, 27))
                .name("Jorge")
                .lastname("Espinola")
                .age(51)
                .build();

        Customer c4=  Customer.builder()
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
