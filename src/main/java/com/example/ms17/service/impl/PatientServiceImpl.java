package com.example.ms17.service.impl;

import com.example.ms17.dto.PatientDto;
import com.example.ms17.exception.PatientNotFound;
import com.example.ms17.mapper.PatientMapper;
import com.example.ms17.model.onetone.Patient;
import com.example.ms17.model.onetone.PatientDetail;
import com.example.ms17.repository.PatientRepository;
import com.example.ms17.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private PatientMapper patientMapper;

    @Override
    public PatientDto save(PatientDto patientDto){

//        Patient patient= modelMapper.map(patientMapper.INSTANCE.patientToPatientDTO(patient), PatientDto.class));
//        patient.getPatientDetail().setPatient(patient);
//        return patientRepository.save(patient);

        Patient patient= patientMapper.INSTANCE.patientDtoToPatient(patientDto);
        PatientDetail patientDetail = patient.getPatientDetail();
//        patient.getPatientDetail().setPatient(patient);
        patientDetail.setPatient(patient);
        return patientMapper.INSTANCE.patientToPatientDTO(patientRepository.save(patient)) ;
//        return patientMapper.INSTANCE.patientToPatientDTO(patient) ;
    };
    @Override
    public List<PatientDto> findAll(){
        return (List<PatientDto>) patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patientMapper.INSTANCE.patientToPatientDTO(patient), PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto findById(long id){
        // return patientRepository.findById(id).get();
        return patientRepository.findById(id)
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .orElseThrow(() -> new PatientNotFound(id));
    };


    @Override
    public PatientDto edit(long id, PatientDto patient){

        Optional<Patient>  ifExits=patientRepository.findById(id);
        if (ifExits.isPresent()) {
            ifExits.get().setMnemonic(patient.getPatientMnemonic());
            ifExits.get().setSurname(patient.getPatientSurname());
            patientRepository.save(ifExits.get());
//            patientRepository.save(patient);
        }
        return ifExits.get();
    }

    @Override
    public Patient deleteById(long id) {
        Optional<Patient> ifExits=patientRepository.findById(id);
//        if (!ifExits.isEmpty()) patientRepository.delete(ifExits.get());
        ifExits.ifPresent(patientRepository::delete);
//        ifExits.ifPresent(patientRepository::delete);
        return ifExits.get();
    }
}
