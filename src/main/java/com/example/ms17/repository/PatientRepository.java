package com.example.ms17.repository;

import com.example.ms17.model.onetone.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Long> {
    @Override
    List<Patient> findAll();

    // JPA field query
    Optional<Patient> findByMnemonic(String mnemonic);
    Optional<Patient> findByMnemonicAndSurname(String mnemonic, String address);

    //Native query

    @Query(value = "Select * from patient",nativeQuery = true)
    List<Patient> findAllNativeQuery();


    @Query(value = "Select * from patient c where c.name=?1",nativeQuery = true)
    List<Patient> findAllNativeQueryQuetsion(String name);
    @Query(value = "Select * from patient c where c.name=:name",nativeQuery = true)
    List<Patient> findAllNativeQueryParam(@Param("name") String name);

    //JPQL
    @Query(value = "Select c from Patient c")
    List<Patient> findAllJPQL();


}
