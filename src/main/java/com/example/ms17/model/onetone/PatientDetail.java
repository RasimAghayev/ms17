package com.example.ms17.model.onetone;

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
public class PatientDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String orderNumber;
    @OneToOne
    @JsonBackReference
    Patient  patient;
}
