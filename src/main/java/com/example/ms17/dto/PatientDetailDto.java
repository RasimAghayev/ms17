package com.example.ms17.dto;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDetailDto {
//    private String patientId;

    private String orderNumber;
}