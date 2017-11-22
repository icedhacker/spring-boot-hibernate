package com.ww.child;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("FEMALE")
@Data
@NoArgsConstructor
public class Daughter extends Child {

    public Daughter(Child child) {
        super(child.getChildId(), child.getName(), child.getAge(), child.getParent(), child.getMeals());
    }

    @Column(name = "color")
    private String hairColor;
}
