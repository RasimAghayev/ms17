package com.example.ms17.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDto{
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private int customerBranch;
}