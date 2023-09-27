package com.example.ms17.service.impl;

import com.example.ms17.dto.ClientDto;
import com.example.ms17.exception.ClientNotFound;
import com.example.ms17.mapper.ClientMapper;
import com.example.ms17.model.onetomany.Client;
import com.example.ms17.repository.ClientRepository;
import com.example.ms17.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private ClientMapper clientMapper;

    @Override
    public ClientDto save(ClientDto clientDto) {

//        return clientMapper.INSTANCE.clientDtoToClient(clientDto);

//        Client client= modelMapper.map(clientMapper.INSTANCE.clientToClientDTO(client), ClientDto.class));
//        return modelMapper.map(clientDto, Client.class);
//        client.getOrders().setClient(client);
//        return clientRepository.save(client);

        Client client = clientMapper.INSTANCE.clientDtoToClient(clientDto);
//        List<Order> order = client.getOrders();
//        order.set(client.getOrders());
//        order.set(client);
        return clientMapper.INSTANCE.clientToClientDTO(clientRepository.save(client));
//        return clientMapper.INSTANCE.clientToClientDTO(client) ;
//        return client;
//        return clientDto;
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(client -> modelMapper.map(clientMapper.INSTANCE.clientToClientDTO(client), ClientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findById(long id) {
        // return clientRepository.findById(id).get();
        return clientRepository.findById(id)
                .map(client -> modelMapper.map(client, ClientDto.class))
                .orElseThrow(() -> new ClientNotFound(id));
    }


    @Override
    public ClientDto edit(long id, ClientDto client) {

        Optional<Client> ifExits = clientRepository.findById(id);
        if (ifExits.isPresent()) {
            ifExits.get().setName(client.getClientName());
            ifExits.get().setSurname(client.getClientSurname());
            clientRepository.save(ifExits.get());
//            clientRepository.save(client);
        }
        return null;
    }

    @Override
    public Client edit(long id, Client client) {

        Optional<Client> ifExits = clientRepository.findById(id);
        if (ifExits.isPresent()) {
            ifExits.get().setName(client.getName());
            ifExits.get().setSurname(client.getSurname());
            clientRepository.save(ifExits.get());
//            clientRepository.save(client);
        }
        return ifExits.get();
    }

    @Override
    public Client deleteById(long id) {
        Optional<Client> ifExits = clientRepository.findById(id);
//        if (!ifExits.isEmpty()) clientRepository.delete(ifExits.get());
        ifExits.ifPresent(clientRepository::delete);
//        ifExits.ifPresent(clientRepository::delete);
        return ifExits.get();
    }


}
