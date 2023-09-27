package com.example.ms17.repository;

import com.example.ms17.model.onetomany.Client;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Client, Long> {
//    List<Client> findAll();

    // JPA field query
//    Optional<Client> findByName(String name);

//    Optional<Client> findByNameAndAddress(String name, String address);

    //Native query

//    @Query(value = "Select * from client", nativeQuery = true)
//    List<Client> findAllNativeQuery();


//    @Query(value = "Select * from client c where c.name=?1", nativeQuery = true)
//    List<Client> findAllNativeQueryQuetsion(String name);

//    @Query(value = "Select * from client c where c.name=:name", nativeQuery = true)
//    List<Client> findAllNativeQueryParam(@Param("name") String name);


    //JPQL
//    @Query(value = "Select c from Client c")
//    List<Client> findAllJPQL();


}
