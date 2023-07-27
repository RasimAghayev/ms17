package com.example.ms17.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto{
    private String customerName;
    private String surname;
    private String address;
    private int branch;

}