package com.ww.child;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meal")
@EntityListeners(AuditingEntityListener.class)
@Data
class Meal {
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
