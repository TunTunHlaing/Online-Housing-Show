package com.example.onlinehousingshowdemo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please Enter Owner  Name.")
    @NotBlank(message = "Please Enter OwnerName")
    private String ownerName;

    @NotNull(message = "Please Enter Email.")
    @NotBlank(message = "Please Enter Email")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Please Enter Password")
    @NotBlank(message = "Please Enter Password")
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    public List<Housing> housingList = new ArrayList<>();

    public void addHousing(Housing housing){
        housing.setOwner(this);
        housingList.add(housing);
    }

    public Owner(String ownerName, String email, String password, LocalDate createdDate, LocalDate updatedDate) {
        this.ownerName = ownerName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerName='" + ownerName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
