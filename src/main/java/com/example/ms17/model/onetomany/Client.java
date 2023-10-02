package com.example.ms17.model.onetomany;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@EqualsAndHashCode()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client")
@Entity
@Data
@ToString
//@EqualsAndHashCode
@NamedEntityGraph(
        name = "Client.orders",
        attributeNodes = @NamedAttributeNode("orders")
)
//@NamedEntityGraph(
//        name = "Client"
//)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String surname;
    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    List<Order> orders;

}
