package com.example.ms17.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
//@Builder
@ToString
public class ClientDto {
    String clientName;
    String clientSurname;
    List<OrderDto> orders;
}