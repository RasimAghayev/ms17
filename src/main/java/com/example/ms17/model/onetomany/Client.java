package com.example.ms17.model.onetomany;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client")
@Entity
//@Data
//@ToString
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
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    /* @JoinColumn(name = "client_id", referencedColumnName = "id") */
            List<Order> orders;
}
