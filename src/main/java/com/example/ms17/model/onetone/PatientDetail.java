package com.example.ms17.model.onetone;

import com.example.ms17.dto.PatientDetailDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "patient_details")
@Entity
@NoArgsConstructor
@ToString
public class PatientDetail extends PatientDetailDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String orderNumber;
    @OneToOne
    @JsonBackReference
    Patient patient;
}
