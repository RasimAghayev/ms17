package com.example.ms17.config;

import com.example.ms17.mapper.CustomerMapper;
import com.example.ms17.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@Slf4j
public class Config {

    @Bean
    public RestTemplate getBean() { return new RestTemplate();}

    @Bean
    public Customer customer() { return new Customer();}

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

}
