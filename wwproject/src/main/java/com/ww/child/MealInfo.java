package com.ww.child;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class MealInfo {
    private Long mealId;

    private String name;

    private Date inventedDate;
}
