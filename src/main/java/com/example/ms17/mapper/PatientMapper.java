package com.example.ms17.mapper;


import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @InheritConfiguration
//    @Mapping(source = "patientMnemonic", target = "mnemonic")
    Patient patientDtoToPatient(PatientDto patoentDto);

//    @InheritConfiguration
//    @Mapping(source = "mnemonic", target = "patientMnemonic")
    PatientDto patientToPatientDTO(Patient patoent);

}