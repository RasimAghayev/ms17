package com.example.ms17.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms17.model.Social;
import com.example.ms17.service.SocialService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/social")
@RequiredArgsConstructor
public class SocialController {
    private final SocialService socialService;
    @PostMapping("/save")
    public Social save(@RequestBody Social social){
        return socialService.save(social);
    }
    @PostMapping("/id/{id}")
    public Social findById(@PathVariable long id){
        return socialService.findById(id);
    }
}
