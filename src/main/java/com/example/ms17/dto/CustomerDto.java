package com.example.ms17.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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