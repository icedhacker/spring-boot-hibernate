package com.ww.child;

import org.springframework.beans.factory.annotation.Autowired;

public class ChildService {
    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private MealRepository mealRepository;
}
