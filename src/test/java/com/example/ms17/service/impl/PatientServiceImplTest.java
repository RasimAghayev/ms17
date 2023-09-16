package com.example.ms17.service.impl;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.exception.PatientNotFound;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PatientServiceImpl patientService;
    private Patient patient;
    private PatientDto patientDto;
    private final List<Patient> patients = new ArrayList<>();

    @BeforeEach
    void setUp() {
        patient= Patient
                .builder()
                .mnemonic("Rasim")
                .surname("Aghayev")
                .build();
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
        patient=null;
    }

    @Test
    @DisplayName("Save Patients")
    void givenPatientThenSaveThenOk() {
        //Arrange - mocking
        when(patientRepository.save(any())).thenReturn(patientDto);

        //Act - call real service
        PatientDto savePatientRes=patientService.save(patientDto);

        //Assert- compare
        assertThat(savePatientRes.getPatientMnemonic()).isEqualTo("Rasim");
        assertThat(savePatientRes.getPatientMnemonic()).isNotEmpty();
        assertThat(savePatientRes.getPatientSurname()).isEqualTo("Aghayev");
        assertThat(savePatientRes.getPatientSurname()).isNotEmpty();

        //Verification
        verify(patientRepository,times(1)).save(any());

    }

    @Test
    @DisplayName("Get Patients list")
    void givenPatientThenFindAllPatientThenOk() {
        //Arrange - mocking
        when(patientRepository.findAll()).thenReturn(patients);

        //Act - call real service
        List<Patient> patientsRes=patientService.findAll();

        //Assert- compare
        assertThat(patientsRes).isNotNull();
        assertThat(patientsRes.size()).isEqualTo(2);

        //Verification
        verify(patientRepository,times(1)).findAll();

    }
    @Test
    @DisplayName("Get Patients by ID")
    void givenPatientIdThenFindPatientThenOk() {
        //Arrange - mocking
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(modelMapper.map(patient,PatientDto.class)).thenReturn(patientDto);

        //Act - call real service
        PatientDto patientRes=patientService.findById(1L);

        //Assert- compare
        assertThat(patientRes.getPatientMnemonic()).isEqualTo("Rasim");

        //Verification
        verify(patientRepository,times(1)).findById(anyLong());
        verify(modelMapper,times(1)).map(any(),any());
    }
    @Test
    @DisplayName("Get Patients by ID Not Found")
    void givenPatientIdThenFindPatientThenNotFound() {
        //Arrange - mocking
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());
//        when(modelMapper.map(patient,PatientDto.class)).thenReturn(patientDto);

        //Act - call real service
//        PatientDto patientRes=patientService.findById(1L);

        assertThatThrownBy(()->patientService.findById(1L))
                .isInstanceOf(PatientNotFound.class)
                .hasMessage("Patient 1 does not exist.");
        //Assert- compare
//        assertThat(patientRes.getName()).isEqualTo("Rasim");

        //Verification
        verify(patientRepository,times(1)).findById(anyLong());
//        verify(modelMapper,times(1)).map(any(),any());
    }


    @Test
    @DisplayName("Edit Patients by ID")
    void givenPatientIdThenEditPatientThenOk() {
        //Arrange - mocking
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        //Act - call real service
        patient.setMnemonic("RaSiM");
        Patient savePatientRes=patientService.edit(anyLong(),patient);

        //Assert- compare
        assertThat(savePatientRes.getMnemonic()).isEqualTo("RaSiM");
        assertThat(savePatientRes.getMnemonic()).isNotEmpty();

        //Verification
        verify(patientRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Delete Patients by ID")
    void givenPatientIdThenDeletePatientThenOk() {
        //Arrange - mocking
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        //Act - call real service
        Patient patientRes=patientService.deleteById(anyLong());

        //Assert- compare
        assertThat(patientRes).isNotNull();

        //Verification
        verify(patientRepository,times(1)).findById(anyLong()); // TODO
//        verify(patientService,times(1)).deleteById(anyLong()); // TODO
    }

}