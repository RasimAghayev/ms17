package com.example.ms17.service.impl;

import com.example.ms17.model.Social;
import com.example.ms17.repository.SocialRepository;
import com.example.ms17.service.SocialService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final SocialRepository socialRepository;
    
    @Override
    public Social save(Social social){
        return socialRepository.save(social);
    };

    @Override
    public Social findById(long id){
        return socialRepository.findById(id).get();
    };
    
    @Override
    public List<Social> findByAll(){
        return null;
    }

}
