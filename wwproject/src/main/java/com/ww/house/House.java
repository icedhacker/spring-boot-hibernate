package com.ww.house;

import com.ww.person.Person;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "house")
@Data
public final class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long houseId;

    @NotBlank
    private String address;

    @NotBlank
    private String zipCode;

    @NotNull
    private HouseType houseType;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person owner;
}

