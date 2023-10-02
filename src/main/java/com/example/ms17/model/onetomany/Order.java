package com.example.ms17.model.onetomany;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client_order")
@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String orderNumber;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "id")
    Client client;
}
