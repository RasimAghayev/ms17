package com.example.ms17;

import com.example.ms17.repository.CustomerRepository;
import com.example.ms17.repository.PatientRepository;
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
    @Value("${has.account}")
    static String hasAccount;
    private final CustomerRepository customerRepository;
    private final PatientRepository patientRepository;
//    private final EntityManagerFactory emf;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Ms17Application.class, args);
//        log.info("Application is starting!");
//
//        log.warn(hasAccount);
    }

    @Override
    public void run(String... args) throws Exception {
//        Customer customer=new Customer();
//        customer.setName("Test");
//        customer.setAddress("Branch Address");
//        customer.setBranch(15);
//        customerRepository.save(customer);

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


//        Patient patient=new Patient();
//        patient.setMnemonic("Test");
//        patient.setSurname("Branch Address");
//
//        PatientDetail patientDetail=new PatientDetail();
//        patientDetail.setOrderNumber("TRX123");
//        patientDetail.setPatient(patient);
//
//        patient.setPatientDetail(patientDetail);
//
//        patientRepository.save(patient);

//        patientRepository.findAll()
//                .forEach(System.out::println);
//
//
//        System.out.println(patientRepository.findByMnemonic("Test"));
//        System.out.println(patientRepository.findByMnemonicAndSurname("Test","Branch Address"));
//
//
//
//
//        patientRepository.findAllNativeQuery()
//                .forEach(System.out::println);
//        patientRepository.findAllNativeQueryQuetsion("Test");
//        patientRepository.findAllNativeQueryParam("Test");


    }

}
