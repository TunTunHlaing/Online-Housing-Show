package com.example.onlinehousingshowdemo.controller;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinehousingshowdemo.entity.Housing;
import com.example.onlinehousingshowdemo.service.HousingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("owner")
@RequiredArgsConstructor
public class OwnerController {

    private final HousingService housingService;


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Housing createHousing(@RequestBody @Valid Housing housing ,
                                 BindingResult result){

        return housingService.createHousing(housing);
    }



    @GetMapping()
    public Page<Housing> viewOwnerHousing(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return housingService.viewOwnerHousing(auth,page, size);
    }


    @PutMapping(value = "{housingId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Housing editHousing(@PathVariable Long housingId,
                               @RequestBody Housing housing){

        return housingService.editHousing(housingId, housing);
    }
}
