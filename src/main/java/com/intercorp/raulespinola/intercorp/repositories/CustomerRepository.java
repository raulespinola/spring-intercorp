package com.intercorp.raulespinola.intercorp.repositories;

import com.intercorp.raulespinola.intercorp.document.CustomerDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDto, String> {
}
