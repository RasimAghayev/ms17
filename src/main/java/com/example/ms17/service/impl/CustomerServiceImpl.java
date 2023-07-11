package com.example.ms17.service.impl;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import com.example.ms17.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Customer deleteById(long id) {
        Optional<Customer> ifExits=customerRepository.findById(id);
//        if (!ifExits.isEmpty()) customerRepository.delete(ifExits.get());
//        if (ifExits.isPresent()) customerRepository.delete(ifExits.get());
        ifExits.ifPresent(customerRepository::delete);
        return ifExits.get();
    }

    @Override
    public Customer edit(long id, Customer customer){

        Optional<Customer>  ifExits=customerRepository.findById(id);
        if (ifExits.isPresent()) {
            ifExits.get().setName(customer.getName());
            customerRepository.save(ifExits.get());
//            customerRepository.save(customer);
        }
        return ifExits.get();
    }
}
