package com.example.ms17.service.impl;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import com.example.ms17.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    
    @Override
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    };

    @Override
    public Customer findById(long id){
        return customerRepository.findById(id).get();
    };
    
    @Override
    public List<Customer> findByAll(){
        return null;
    }

}
