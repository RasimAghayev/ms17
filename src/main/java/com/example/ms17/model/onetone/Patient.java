package com.example.ms17.model.onetone;

import com.example.ms17.dto.PatientDto;
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
public class Patient extends PatientDto {
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patient")
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public PatientDetail patientDetail;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String mnemonic;
    String surname;
}
