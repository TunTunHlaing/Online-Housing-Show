package com.example.onlinehousingshowdemo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.onlinehousingshowdemo.entity.Housing;


public interface HousingRepo extends JpaRepository<Housing, Long>, PagingAndSortingRepository<Housing, Long> {


    Page<Housing> findAll(Specification<Housing> housing, Pageable pageable);

    Page<Housing> findByOwnerId(Long id, Pageable pageable);


}


