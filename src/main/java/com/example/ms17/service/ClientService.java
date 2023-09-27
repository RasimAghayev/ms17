package com.example.ms17.service;

import com.example.ms17.dto.ClientDto;
import com.example.ms17.model.onetomany.Client;

import java.util.List;

public interface ClientService {

//    Client save(Client customer);

    ClientDto save(ClientDto clientDto);

    List<ClientDto> findAll();

    ClientDto findById(long id);


    ClientDto edit(long id, ClientDto clientDto);

    Client deleteById(long id);

    Client edit(long id, Client client);


}
