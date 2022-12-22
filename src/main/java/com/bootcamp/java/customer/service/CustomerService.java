package com.bootcamp.java.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.java.customer.domain.Customer;
import com.bootcamp.java.customer.repository.CustomerRepository;
import com.bootcamp.java.customer.web.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService  {

    
    @Autowired
    private CustomerRepository customerRepository;
    
	@Autowired
    private CustomerMapper customerMapper;

    
	public Flux<Customer> findAll(){
    	log.debug("findAll executed");
        return customerRepository.findAll();
    }

    
	public Mono<Customer> findById(String customerId){
    	log.debug("findById executed {}", customerId);
        return customerRepository.findById(customerId);
    }

    
	public Mono<Customer> create(Customer customer){
    	log.debug("create executed {}", customer);    	
    	return customerRepository.save(customer);    		
    }

    
	public Mono<Customer> update(String customerId,  Customer customer){
    	log.debug("update executed {}:{}", customerId, customer);
        return customerRepository.findById(customerId)
                .flatMap(dbCustomer -> {
                	customerMapper.update(dbCustomer, customer);
                    return customerRepository.save(dbCustomer);
                });
    }

    
	public Mono<Customer> delete(String customerId){
    	log.debug("delete executed {}", customerId);
        return customerRepository.findById(customerId)
                .flatMap(existingCustomer -> customerRepository.delete(existingCustomer)
                .then(Mono.just(existingCustomer)));
    }
  
}

