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
        Customer customerA = new Customer(
                UUID.randomUUID(),
                "Raul",
                "Espinola",
                37,
                LocalDate.of(1982, 10, 27)
        );

        Customer customerB = new Customer(
                UUID.randomUUID(),
                "Jorge",
                "Espinola",
                37,
                LocalDate.of(1982, 10, 27)
        );
        //drop all customers
        this.customerRepository.deleteAll();

        //Add customers to DB
        List<Customer> customers = Arrays.asList(customerA, customerB);
        this.customerRepository.saveAll(customers);
    }
}
