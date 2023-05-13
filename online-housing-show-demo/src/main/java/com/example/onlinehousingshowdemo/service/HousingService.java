package com.example.onlinehousingshowdemo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.onlinehousingshowdemo.ExceptionHandle.DataNotFoundException;
import com.example.onlinehousingshowdemo.entity.Housing;
import com.example.onlinehousingshowdemo.entity.Owner;
import com.example.onlinehousingshowdemo.repo.HousingRepo;
import com.example.onlinehousingshowdemo.repo.OwnerRepo;

@Service
public class HousingService {

    @Autowired
    private HousingRepo housingRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Transactional
    public Housing createHousing(Housing housing) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = ownerRepo
                .findOwnerByEmail(auth.getName()).orElseThrow(() -> new DataNotFoundException("User Not Found!"));
        owner.addHousing(housing);
        return housingRepo.save(housing);
    }

    public List<Housing> housingList(Long ownerId) {
        Owner owner =ownerRepo.findById(ownerId).get();
        return owner.housingList;
    }

    @Transactional
    public Housing editHousing(Long housingId, Housing h) {

        Housing excitedHousing = housingRepo.findById(housingId)
                .orElseThrow(() -> new DataNotFoundException("Housing Not Found!"));
        excitedHousing.setHousingName(h.getHousingName());
        excitedHousing.setAddress(h.getAddress());
        excitedHousing.setNumberOfFloors(h.getNumberOfFloors());
        excitedHousing.setNumberOfMasterRoom(h.getNumberOfMasterRoom());
        excitedHousing.setNumberOfSingleRoom(h.getNumberOfSingleRoom());
        excitedHousing.setAmount(h.getAmount());
        return housingRepo.save(excitedHousing);
    }

    public Page<Housing> search(int page , int size,Optional<String> housingName, Optional<Integer> numberOfFloors, Optional<Integer> numberOfMasterRooms, Optional<Integer> numberOfSingleRooms, Optional<Double> amount, Optional<LocalDate> createdDate) {


        Pageable pageable = PageRequest.of(page, size);
        Page<Housing> housingList = housingRepo.findAll(
                whichHousingName(housingName).
                        and(whichNumberOfFloors(numberOfFloors).
                                and(whichNumberOfMasterRoom(numberOfMasterRooms).
                                        and(whichNumberOfSingleRooms(numberOfSingleRooms).
                                                and(whichAmount(amount).
                                                        and(whichCreatedDate(createdDate)))))), pageable);

        return housingList;
    }


    public Specification<Housing> whichHousingName(Optional<String> housingName){
        return housingName.isEmpty() ? Specification.where(null) :
                (root, query, cb) -> cb.like
                        (cb.lower(root.get("housingName")), housingName.get()
                                .toLowerCase().concat("%"));
    }
    public Specification<Housing> whichNumberOfFloors(Optional<Integer> numberOfFloors){
        return !numberOfFloors.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfFloors"), numberOfFloors.get());
    }

    public Specification<Housing> whichNumberOfMasterRoom(Optional<Integer> numberOfMasterRooms){
        return !numberOfMasterRooms.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfMasterRoom"),
                        numberOfMasterRooms.get());
    }

    public Specification<Housing> whichNumberOfSingleRooms(Optional<Integer> numberOfSingleRooms){
        return !numberOfSingleRooms.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("numberOfSingleRoom"),
                        numberOfSingleRooms.get());
    }

    public Specification<Housing> whichAmount(Optional<Double> amount){
        return !amount.isPresent()? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("amount"), amount.get());
    }

    public Specification<Housing> whichCreatedDate(Optional<LocalDate> createdDate) {
        return !createdDate.isPresent() ? Specification.where(null) :
                (root, query, cb) -> cb.equal(root.get("createdDate"), createdDate.get());
    }

    public Page<Housing> viewOwnerHousing(Authentication auth, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String email = auth.getName();
        Owner owner = ownerRepo.findOwnerByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        return housingRepo.findByOwnerId(owner.getId(), pageable);

    }
}
