package com.example.ms17.controller;

//import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id/{id}")
    public Customer findById(@PathVariable long id){
        return customerService.findById(id);
    }

    @GetMapping("/delete")
    public Customer deleteById(@Param("id") long id){
        return customerService.deleteById(id);
    }

    @PutMapping("/edit/id/{id}")
    public Customer edit(@RequestBody Customer customer,@PathVariable long id){
        return customerService.edit(id, customer);
    }
}
