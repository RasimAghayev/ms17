package com.example.ms17.service;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;

import java.util.List;

public interface PatientService {

//    Patient save(Patient customer);

    PatientDto save(PatientDto patientDto);

    List<PatientDto> findAll();

    PatientDto findById(long id);

    Patient deleteById(long id);

    PatientDto edit(long id, PatientDto patientDto);

}
