package com.example.onlinehousingshowdemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(value = "housing_name")
    @NotNull(message = "Please Enter Housing Name.")
    @NotBlank(message = "Please Enter Housing Name")
    private String housingName;

    @NotNull(message = "Please enter address.")
    @NotBlank(message = "Please Enter Address")
    @JsonProperty("address")
    private String address;

    @NotNull(message = "Please enter number of floors.")
    @JsonProperty("number_of_floors")
    private Integer numberOfFloors;

    @NotNull(message = "Please enter number of master room.")
    @JsonProperty("number_of_master_room")
    private Integer numberOfMasterRoom;

    @NotNull(message = "Please enter number of single rooms.")
    @JsonProperty("number_of_single_room")
    private Integer numberOfSingleRoom;

    @NotNull(message = "Please enter amount.")
    @JsonProperty("amount")
    private Double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("created_date")
    private LocalDate createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("updated_date")
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Housing(String housingName, String address, Integer numberOfFloors, Integer numberOfMasterRoom, Integer numberOfSingleRoom,
                   Double amount, LocalDate createdDate, LocalDate updatedDate) {
        this.housingName = housingName;
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.numberOfMasterRoom = numberOfMasterRoom;
        this.numberOfSingleRoom = numberOfSingleRoom;
        this.amount = amount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "housingName='" + housingName + '\'' +
                ", address='" + address + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                ", numberOfMasterRoom=" + numberOfMasterRoom +
                ", numberOfSingleRoom=" + numberOfSingleRoom +
                ", amount=" + amount +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
