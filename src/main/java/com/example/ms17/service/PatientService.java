package com.example.ms17.service;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.model.onetone.Patient;

import java.util.List;

public interface PatientService {

//    Patient save(Patient customer);

    PatientDto save(PatientDto customerDto);

    List<Patient> findAll();

    PatientDto findById(long id);

    Patient deleteById(long id);

    Patient edit(long id, Patient customer);

}
