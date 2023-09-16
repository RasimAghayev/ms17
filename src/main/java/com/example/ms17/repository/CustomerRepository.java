package com.example.ms17.repository;

import com.example.ms17.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findAll();

    // JPA field query
    Optional<Customer> findByName(String name);
    Optional<Customer> findByNameAndAddress(String name, String address);

    //Native query

    @Query(value = "Select * from customer",nativeQuery = true)
    List<Customer> findAllNativeQuery();


    @Query(value = "Select * from customer c where c.name=?1",nativeQuery = true)
    List<Customer> findAllNativeQueryQuetsion(String name);
    @Query(value = "Select * from customer c where c.name=:name",nativeQuery = true)
    List<Customer> findAllNativeQueryParam(@Param("name") String name);


    //JPQL
    @Query(value = "Select c from Customer c")
    List<Customer> findAllJPQL();


}
