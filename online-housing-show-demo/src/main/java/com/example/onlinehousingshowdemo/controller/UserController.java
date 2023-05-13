package com.example.onlinehousingshowdemo.controller;

import com.example.onlinehousingshowdemo.ExceptionHandle.ValidationException;
import com.example.onlinehousingshowdemo.entity.AuthRequest;
import com.example.onlinehousingshowdemo.entity.Housing;
import com.example.onlinehousingshowdemo.entity.Owner;
import com.example.onlinehousingshowdemo.service.HousingService;
import com.example.onlinehousingshowdemo.service.OwnerService;
import com.example.onlinehousingshowdemo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final OwnerService service;

    private final HousingService housingService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtil;

    @PostMapping(value = "register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Owner register(Owner owner , BindingResult result){
        if (result.hasErrors()){
            throw new ValidationException(result.getFieldErrors());
        }

        return service.register(owner);
    }


    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }


    @GetMapping("{page}/{size}")
    public Page<Housing> searchHousingPagination(
            @PathVariable int page,
            @PathVariable int size,
            @RequestParam Optional<String> housingName,
            @RequestParam Optional<Integer> numberOfFloors,
            @RequestParam  Optional<Integer> numberOfMasterRooms,
            @RequestParam Optional<Integer> numberOfSingleRooms,
            @RequestParam  Optional<Double> amount,
            @RequestParam Optional<LocalDate> createdDate
    ){
        return housingService.search(page, size, housingName,numberOfFloors,
                numberOfMasterRooms,numberOfSingleRooms,amount,createdDate);
    }

}
