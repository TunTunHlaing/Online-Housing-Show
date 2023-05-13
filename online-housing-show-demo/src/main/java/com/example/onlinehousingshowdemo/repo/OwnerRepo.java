package com.example.onlinehousingshowdemo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.onlinehousingshowdemo.entity.Owner;

public interface OwnerRepo extends JpaRepositoryImplementation<Owner, Long> {

    Optional<Owner> findOwnerByEmail(String email);

}
