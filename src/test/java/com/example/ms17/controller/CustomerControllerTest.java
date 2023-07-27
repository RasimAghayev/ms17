package com.example.ms17.controller;

import com.example.ms17.dto.CustomerDto;
import com.example.ms17.model.Customer;
import com.example.ms17.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private ModelMapper modelMapper;
//    private Customer customer;
    private CustomerDto customerDto;
    private final List<Customer> customers = new ArrayList<>();
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        customerDto= CustomerDto
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
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // @Test
    @DisplayName("Save Customers Controller")
    void givenCustomerThenSaveThenOk() throws Exception {
        //Arrange - mocking
//        when(customerService.save(customer)).thenReturn(customer);

        String expectedJson = new ObjectMapper()
                .writeValueAsString(customerDto);

        //Act - call real service
        mockMvc.perform(
                        post("/customer")
                                .content(expectedJson)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.branch").exists());

        //Assert- compare

        //Verification
//        verify(customerService,times(1)).findById(anyLong());
    }
    ///https://github.com/lokeshgupta1981/SpringExamples/blob/master/unit-testing/src/test/java/com/howtodoinjava/rest/RestTemplatePostApiExamples.java

    @Test
    @DisplayName("Get Customers by ID")
    void givenIdThenFindCustomerThenOk() throws Exception {
        //Arrange - mocking
        when(customerService.findById(anyLong())).thenReturn(customerDto);

        //Act - call real service
       mockMvc.perform(
               get("/customer/{id}",1L)
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

//    @Test
    void deleteById() {
    }

//    @Test
    void edit() {
    }
}