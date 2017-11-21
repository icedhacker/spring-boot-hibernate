package com.ww.child;

import com.ww.WawiApplication;
import com.ww.person.Person;
import com.ww.util.DataLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WawiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ChildServiceTest {

    @Autowired
    private ChildService childService;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private DataLoader dataLoader;

    @Test
    public void whenRetrieveChildById_returnChildInfo() {
        Person parent = dataLoader.createSamplePerson(1);
        Child child = dataLoader.createSampleChild(1);
        List<Meal> mealList = new ArrayList<>();
        Meal meal = dataLoader.createSampleMeal(1);
        mealList.add(meal);

        Meal meal2 = dataLoader.createSampleMeal(2);
        mealList.add(meal2);

        Meal meal3 = dataLoader.createSampleMeal(3);
        mealList.add(meal3);
        mealList = dataLoader.addMeals(parent, child, mealList);
        Meal expectedMeal = mealList.get(0);

        ChildInfo childInfo = childService.getChildInfo(1L);
        assertThat(childInfo).isNotNull();
        assertThat(childInfo.getChildId()).isEqualTo(expectedMeal.getChild().getChildId());
        assertThat(childInfo.getMeal().getMealId()).isEqualTo(expectedMeal.getMealId());
    }
}
