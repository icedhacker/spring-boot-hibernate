package com.ww.child;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parent_summary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parentSummaryId;

    @NotNull
    private Long amountOfPersons;

    @NotNull
    private Long amountOfChildren;

    public ParentSummary(Long amountOfPersons, Long amountOfChildren) {
        this.amountOfPersons = amountOfPersons;
        this.amountOfChildren = amountOfChildren;
    }
}
