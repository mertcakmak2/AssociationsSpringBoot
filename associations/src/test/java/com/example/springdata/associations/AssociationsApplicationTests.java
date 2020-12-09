package com.example.springdata.associations;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdata.associations.entities.Customer;
import com.example.springdata.associations.entities.PhoneNumber;
import com.example.springdata.associations.repos.CustomerRepository;

@SpringBootTest
class AssociationsApplicationTests {

	@Autowired
	CustomerRepository repository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("Mert");
//		HashSet<PhoneNumber> numbers = new HashSet<PhoneNumber>();
		
		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("111");
		ph1.setType("cell");
		
//		ph1.setCustomer(customer);
//		numbers.add(ph1);
		
		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("222");
		ph2.setType("home");
//		ph2.setCustomer(customer);
//		numbers.add(ph2);
		
//		customer.setNumbers(numbers);
		
		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);

		repository.save(customer);
	}
	
	@Test
	@Transactional
	public void testLoadCustomer() {
		Customer customer = repository.findById(11L).get();
		System.out.println(customer.getName());
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(x -> System.out.println(x.getNumber()));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateCustomer() {
		Customer customer = repository.findById(11L).orElse(null);
		customer.setName("Aiden Cakmaak");
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number -> number.setType("workk"));
		
		repository.save(customer);
	}
	
	@Test
	public void testDelete() {
		repository.deleteById(8l);
	}

}
