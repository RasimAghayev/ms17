package com.example.ms17.controller;

import com.example.ms17.dto.CustomerDto;
import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("")
    public CustomerDto save(@RequestBody CustomerDto customerDto) {
        return customerService.save(customerDto);
    }


    @Operation(summary = "Get all a customer list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Found all customer",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "Customers not found",
                    content = @Content)
    }
    )
    @GetMapping("")
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @Operation(summary = "Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Found the customer",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "Customer not found",
                    content = @Content)
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }


    @Operation(summary = "Update a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Update this customer",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "Customer not found",
                    content = @Content)
    }
    )
    @PutMapping("/{id}")
    public Customer edit(@PathVariable long id, @RequestBody Customer customer) {
        return customerService.edit(id, customer);
    }


    @Operation(summary = "Delete a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Delete this customer",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Customer.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "Customer not found",
                    content = @Content)
    }
    )
    @DeleteMapping("/{id}")
    public Customer deleteById(@PathVariable long id) {
        return customerService.deleteById(id);
    }
}
