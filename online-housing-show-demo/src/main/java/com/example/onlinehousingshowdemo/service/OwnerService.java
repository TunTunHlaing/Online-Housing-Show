package com.example.onlinehousingshowdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.onlinehousingshowdemo.entity.Owner;
import com.example.onlinehousingshowdemo.repo.OwnerRepo;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepo repo;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public Owner register(Owner owner) {
        String pass = encoder.encode(owner.getPassword());
        owner.setPassword(pass);
        return repo.save(owner);
    }


}
