package com.ww.child;

import com.ww.person.Person;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ChildInfo {
    private Long childId;

    private String name;

    private Integer age;

    private Person parent;

    private MealInfo mealInfo;
}
