package com.example.ms17.mapper;


import com.example.ms17.dto.PatientDto;
import com.example.ms17.model.onetone.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(target = "mnemonic", source = "patientMnemonic"),
            @Mapping(target = "surname", source = "patientSurname"),
            @Mapping(target = "patientDetail", source = "patientDetail")
    })
    @InheritConfiguration
    Patient patientDtoToPatient(PatientDto patientDto);

    @Mappings({
            @Mapping(target = "patientMnemonic", source = "mnemonic"),
            @Mapping(target = "patientSurname", source = "surname"),
            @Mapping(target = "patientDetail", source = "patientDetail")
    })
    PatientDto patientToPatientDTO(Patient patient);

}