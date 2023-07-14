package com.example.ms17.controller;

import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
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
    }

    @Test
    void save() {
    }

    @Test
    @DisplayName("Get Customers by ID")
    void givenIdThenFindCustomerThenOk() throws Exception {
        //Arrange - mocking
        when(customerService.findById(anyLong())).thenReturn(customer);

        //Act - call real service
       mockMvc.perform(
               get("/customer/id/{id}",1L)
                       .accept(APPLICATION_JSON)
                       .contentType(APPLICATION_JSON)
       )
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Rasim"))
               .andExpect(jsonPath("$.surname").value("Aghayev"))
               .andExpect(jsonPath("$.address").value("Baki"))
               .andExpect(jsonPath("$.branch").value(15))
               .andDo(print())
       ;

        //Assert- compare

        //Verification
        verify(customerService,times(1)).findById(anyLong());
    }

    @Test
    void deleteById() {
    }

    @Test
    void edit() {
    }
}