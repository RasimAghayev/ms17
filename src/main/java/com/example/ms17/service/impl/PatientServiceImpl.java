package com.example.ms17.service.impl;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.exception.PatientNotFound;
import com.example.ms17.mapper.PatientMapper;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.repository.PatientRepository;
import com.example.ms17.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private PatientMapper patientMapper;

    @Override
    public PatientDto save(PatientDto patientDto){
        Patient patient= patientMapper.INSTANCE.patientDtoToPatient(patientDto);
        return patientMapper.INSTANCE.patientToPatientDTO(patientRepository.save(patient)) ;
    };
    @Override
    public List<Patient> findAll(){
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public PatientDto findById(long id){
        // return patientRepository.findById(id).get();
        return patientRepository.findById(id)
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .orElseThrow(() -> new PatientNotFound(id));
    };


    @Override
    public Patient edit(long id, Patient patient){

        Optional<Patient>  ifExits=patientRepository.findById(id);
        if (ifExits.isPresent()) {
            ifExits.get().setMnemonic(patient.getMnemonic());
            patientRepository.save(ifExits.get());
//            patientRepository.save(patient);
        }
        return ifExits.get();
    }

    @Override
    public Patient deleteById(long id) {
        Optional<Patient> ifExits=patientRepository.findById(id);
//        if (!ifExits.isEmpty()) patientRepository.delete(ifExits.get());
        if (ifExits.isPresent()) patientRepository.delete(ifExits.get());
//        ifExits.ifPresent(patientRepository::delete);
        return ifExits.get();
    }
}
