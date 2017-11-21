package com.ww.person;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
@Data
public final class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}
