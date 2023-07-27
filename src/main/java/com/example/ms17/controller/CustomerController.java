package com.example.ms17.controller;

import org.springframework.web.bind.annotation.*;

import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("")
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping("")
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable long id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PutMapping("/{id}")
    public Customer edit(@PathVariable long id,@RequestBody Customer customer){
        return customerService.edit(id, customer);
    }

    @DeleteMapping("/{id}")
    public Customer deleteById(@PathVariable long id){
        return customerService.deleteById(id);
    }
}
