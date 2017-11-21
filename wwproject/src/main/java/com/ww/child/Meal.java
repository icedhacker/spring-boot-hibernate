package com.ww.child;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meal")
@Getter
@Setter
public final class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mealId;

    @NotBlank
    private String name;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date inventedDate;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;
}
