package com.ww.child;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ww.person.Person;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "child")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Data
class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long childId;

    @NotBlank
    private String name;

    @NotBlank
    private Integer age;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Person parent;

    @OneToMany(mappedBy = "child")
    private List<Meal> meals;
}
