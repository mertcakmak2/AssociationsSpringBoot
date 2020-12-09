package com.example.springdata.associations.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.springdata.associations.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
