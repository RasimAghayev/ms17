package com.example.ms17.model.onetone;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
//@EqualsAndHashCode
//@NamedEntityGraph(
//        name = "PatientDetail.customer",
//        attributeNodes = @NamedAttributeNode("client")
//)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String mnemonic;
    String surname;
    @OneToOne (cascade = CascadeType.ALL,mappedBy = "patient")
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    PatientDetail patientDetails;
}
