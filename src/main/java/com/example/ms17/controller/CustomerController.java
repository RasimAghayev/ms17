package com.example.ms17.controller;

import com.example.ms17.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    public CustomerDto save(@RequestBody CustomerDto customerDto){
        return customerService.save(customerDto);
    }

    @GetMapping("")
    public List<CustomerDto> findAll(){
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
