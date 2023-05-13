package com.example.onlinehousingshowdemo.service;

import com.example.onlinehousingshowdemo.ExceptionHandle.DataNotFoundException;
import com.example.onlinehousingshowdemo.entity.Owner;
import com.example.onlinehousingshowdemo.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OwnerRepo ownerRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Owner user = ownerRepo.findOwnerByEmail(s)
                .orElseThrow(() -> new DataNotFoundException("User Not Found!"));
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
