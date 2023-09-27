package com.example.ms17.controller;

import com.example.ms17.dto.ClientDto;
import com.example.ms17.model.onetomany.Client;
import com.example.ms17.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping("")
    public ClientDto save(@RequestBody ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @GetMapping("")
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping("/{id}")
    public ClientDto edit(@PathVariable long id, @RequestBody ClientDto clientDto) {
        return clientService.edit(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public Client deleteById(@PathVariable long id) {
        return clientService.deleteById(id);
    }

}
