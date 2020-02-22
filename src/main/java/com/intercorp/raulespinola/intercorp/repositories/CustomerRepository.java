package com.intercorp.raulespinola.intercorp.repositories;

import com.intercorp.raulespinola.intercorp.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
