package com.example.ms17.service;

import com.example.ms17.dto.CustomerDto;
import com.example.ms17.model.Customer;
import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    CustomerDto findById(long id);


    Customer deleteById(long id);

    Customer edit(long id, Customer customer);


}
