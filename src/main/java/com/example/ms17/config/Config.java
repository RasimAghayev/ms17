package com.example.ms17.config;

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

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategy.STRICT);

        return modelMapper;
    }
    
}
