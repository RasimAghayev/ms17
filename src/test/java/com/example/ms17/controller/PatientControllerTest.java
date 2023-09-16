package com.example.ms17.controller;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private ModelMapper modelMapper;
    private Patient patient;
    private PatientDto patientDto;
    private final List<Patient> patients = new ArrayList<>();
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        patientDto= PatientDto
                .builder()
                .patientMnemonic("Rasim")
                .patientSurname("Aghayev")
                .build();
        Patient patient1 = Patient
                .builder()
                .mnemonic("Rasim1")
                .surname("Aghayev1")
                .build();
        Patient patient2 = Patient
                .builder()
                .mnemonic("Rasim2")
                .surname("Aghayev2")
                .build();
        patients.add(patient1);
        patients.add(patient2);
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

    @Test
    @DisplayName("Save Patients Controller")
    void givenPatientThenSaveThenOk() throws Exception {
        //Arrange - mocking
        when(patientService.save(patientDto)).thenReturn(patientDto);

        String expectedJson = new ObjectMapper()
                .writeValueAsString(patientDto);

        //Act - call real service
        mockMvc.perform(
                        post("/patients")
                                .content(expectedJson)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists());

        //Assert- compare

        //Verification
        verify(patientService,times(1)).save(patientDto);
    }
    ///https://github.com/lokeshgupta1981/SpringExamples/blob/master/unit-testing/src/test/java/com/howtodoinjava/rest/RestTemplatePostApiExamples.java

    @Test
    @DisplayName("Get Patients by ID")
    void givenIdThenFindPatientThenOk() throws Exception {
        //Arrange - mocking
        when(patientService.findById(anyLong())).thenReturn(patientDto);

        //Act - call real service
        mockMvc.perform(
                        get("/patients/{id}",1L)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("Rasim"))
                .andExpect(jsonPath("$.surname").value("Aghayev"))
                .andDo(print())
        ;

        //Assert- compare

        //Verification
        verify(patientService,times(1)).findById(anyLong());
    }

    //    @Test
    void deleteById() {
    }

    //    @Test
    void edit() {
    }
}