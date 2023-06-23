package com.example.ms17.service;

import com.example.ms17.model.Social;
import java.util.List;

public interface SocialService {

    Social save(Social social);
    
    Social findById(long id);
    
    List<Social> findByAll();

}
