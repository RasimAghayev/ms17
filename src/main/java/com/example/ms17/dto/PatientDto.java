package com.example.ms17.dto;

import com.example.ms17.model.onetone.PatientDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDto {
    private String patientMnemonic;
    private String patientSurname;
    private PatientDetailDto patientDetail;
}