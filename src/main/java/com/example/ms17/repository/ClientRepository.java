package com.example.ms17.repository;

import com.example.ms17.model.onetomany.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    //    @EntityGraph(value = "Client.orders")
    @Query(value = "Select c from Client c join fetch c.orders o")
    List<Client> findAll();

//    @EntityGraph(value = "Client")
//    List<Client> findAlls();

    // JPA field query
    @EntityGraph(value = "Client.orders")
    Optional<Client> findById(long id);

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
