package com.example.ms17.service.impl;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;
    private Customer customer;
    private final List<Customer> customers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customer= Customer
                .builder()
                .name("Rasim")
                .surname("Aghayev")
                .address("Baki")
                .branch(15)
                .build();
        Customer customer1 = Customer
                .builder()
                .name("Rasim1")
                .surname("Aghayev1")
                .address("Baki1")
                .branch(151)
                .build();
        Customer customer2 = Customer
                .builder()
                .name("Rasim2")
                .surname("Aghayev2")
                .address("Baki2")
                .branch(152)
                .build();
        customers.add(customer1);
        customers.add(customer2);
    }

    @AfterEach
    void tearDown() {
        customer=null;
    }

    @Test
    @DisplayName("Save Customers")
    void givenCustomerThenSaveThenOk() {
        //Arrange - mocking
        when(customerRepository.save(any())).thenReturn(customer);

        //Act - call real service
        Customer saveCustomerRes=customerService.save(customer);

        //Assert- compare
        assertThat(saveCustomerRes.getName()).isEqualTo("Rasim");
        assertThat(saveCustomerRes.getName()).isNotEmpty();
        assertThat(saveCustomerRes.getSurname()).isEqualTo("Aghayev");
        assertThat(saveCustomerRes.getSurname()).isNotEmpty();

        //Verification
        verify(customerRepository,times(1)).save(any());

    }

    @Test
    @DisplayName("Get Customers list")
    void givenCustomerThenFindAllCustomerThenOk() {
        //Arrange - mocking
        when(customerRepository.findAll()).thenReturn(customers);

        //Act - call real service
        List<Customer> customersRes=customerService.findAll();

        //Assert- compare
        assertThat(customersRes).isNotNull();
        assertThat(customersRes.size()).isEqualTo(2);

        //Verification
        verify(customerRepository,times(1)).findAll();

    }
    @Test
    @DisplayName("Get Customers by ID")
    void givenCustomerIdThenFindCustomerThenOk() {
        //Arrange - mocking
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //Act - call real service
        Customer customerRes=customerService.findById(1l);

        //Assert- compare
        assertThat(customerRes.getName()).isEqualTo("Rasim");

        //Verification
        verify(customerRepository,times(1)).findById(anyLong());
    }


    @Test
    @DisplayName("Edit Customers by ID")
    void givenCustomerIdThenEditCustomerThenOk() {
        //Arrange - mocking
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //Act - call real service
        customer.setName("RaSiM");
        Customer saveCustomerRes=customerService.edit(anyLong(),customer);

        //Assert- compare
        assertThat(saveCustomerRes.getName()).isEqualTo("RaSiM");
        assertThat(saveCustomerRes.getName()).isNotEmpty();

        //Verification
        verify(customerRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Delete Customers by ID")
    void givenCustomerIdThenDeleteCustomerThenOk() {
        //Arrange - mocking
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        //Act - call real service
        Customer customerRes=customerService.deleteById(anyLong());

        //Assert- compare
        assertThat(customerRes).isNotNull();

        //Verification
        verify(customerRepository,times(1)).findById(anyLong()); // TODO
//        verify(customerService,times(1)).deleteById(anyLong()); // TODO
    }

}