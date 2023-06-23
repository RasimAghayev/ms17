package com.example.ms17.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }
    @PostMapping("/id/{id}")
    public Customer findById(@PathVariable long id){
        return customerService.findById(id);
    }
}
