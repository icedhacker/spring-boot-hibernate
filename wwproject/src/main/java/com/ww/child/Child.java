package com.ww.child;

import com.ww.person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "child")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "gender")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long childId;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person parent;

    @OneToMany(mappedBy = "child", fetch = FetchType.EAGER)
    @OrderBy("mealId ASC")
    private List<Meal> meals;
}
