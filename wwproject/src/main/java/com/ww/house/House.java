package com.ww.house;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ww.person.Person;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "house")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Data
class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long houseId;

    @NotBlank
    private String address;

    @NotBlank
    private String zipCode;

    @NotBlank
    private HouseType houseType;

    @OneToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Person owner;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}

