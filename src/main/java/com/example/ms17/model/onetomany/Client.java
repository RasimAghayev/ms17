package com.example.ms17.model.onetomany;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client")
@Entity
@NoArgsConstructor
//@EqualsAndHashCode
//@NamedEntityGraph(
//        name = "PatientDetail.customer",
//        attributeNodes = @NamedAttributeNode("client")
//)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String surname;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)
    List<Order> orders;


}
