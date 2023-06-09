package com.example.ms17.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ms17 {
    String name;
    String surname;


    public static void main(String[] args) {

      Ms17 ms=  new Ms17();
      ms.setName("Celal");

        System.out.println(new Ms17().getName());
    }

}


