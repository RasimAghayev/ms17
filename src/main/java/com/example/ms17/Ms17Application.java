package com.example.ms17;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Ms17Application implements CommandLineRunner {
    private final CustomerRepository customerRepository;
//    private final EntityManagerFactory emf;

    @Value("${has.account}")
    static String hasAccount;
    public static void main(String[] args)  throws InterruptedException {
        SpringApplication.run(Ms17Application.class, args);
//        log.info("Application is starting!");
//
//        log.warn(hasAccount);
    }
    @Override
    public void run(String... args) throws Exception{
        Customer customer=new Customer();
        customer.setName("Test");
        customer.setAddress("Branch Address");
        customer.setBranch(15);
       customerRepository.save(customer);

//        customerRepository.findAll()
//                .forEach(System.out::println);
//
//
//        System.out.println(customerRepository.findByName("Test"));
//        System.out.println(customerRepository.findByNameAndAddress("Test","Branch Address"));
//
//
//
//
//        customerRepository.findAllNativeQuery()
//                .forEach(System.out::println);
//        customerRepository.findAllNativeQueryQuetsion("Test");
//        customerRepository.findAllNativeQueryParam("Test");
    }

}
