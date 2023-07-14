package com.example.ms17.service.impl;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    void setUp() {
        customer= Customer
                .builder()
                .name("Rasim")
                .surname("Aghayev")
                .address("Baki")
                .branch(15)
                .build();
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
    void findByAll() {
    }

    @Test
    void deleteById() {
        //Arrange - mocking //Arrange - mocking
//        when(customerRepository.save(any())).thenReturn(customer);
//
//        //Act - call real service
//        Customer saveCustomerRes=customerService.save(customer);
//
//        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
//
//        when(customerRepository.deleteById(anyLong())).thenReturn(customer);
//
//        //Act - call real service
//        Customer customerRes=customerService.findById(1l);
//
//        //Assert- compare
//        assertThat(customerRes.getName()).isEqualTo("Rasim");
//
//        //Verification
//        verify(customerRepository,times(1)).findById(anyLong());
    }

    @Test
    void edit() {
    }
}