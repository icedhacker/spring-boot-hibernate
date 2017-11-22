package com.ww.child;

import com.ww.WawiApplication;
import com.ww.person.Person;
import com.ww.util.DataLoader;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ChildServiceTest {
    @Autowired
    private ChildService childService;

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

    @Test
    public void whenRetrieveColorByChildIdForSon_returnColorInfo() {
        Person parent = dataLoader.createSamplePerson(1);
        Child child = dataLoader.createSampleChild(1);
        Son son = new Son(child);
        son.setBicycleColor("Red");
        Son expectedSon = (Son)dataLoader.addChild(parent, son);
        ColorInfo colorInfo = childService.getColorInfo(1L);
        assertThat(colorInfo).isNotNull();
        assertThat(colorInfo.getChildId()).isEqualTo(expectedSon.getChildId());
        assertThat(colorInfo.getColor()).isEqualTo("Red");
        assertThat(colorInfo.getColorType()).isEqualTo(ColorType.BICYCLE_COLOR);
    }

    @Test
    public void whenRetrieveColorByChildIdForDaughter_returnColorInfo() {
        Person parent = dataLoader.createSamplePerson(1);
        Child child = dataLoader.createSampleChild(1);
        Daughter daughter = new Daughter(child);
        daughter.setHairColor("Blue");
        Daughter expectedDaughter = (Daughter)dataLoader.addChild(parent, daughter);
        ColorInfo colorInfo = childService.getColorInfo(1L);
        assertThat(colorInfo).isNotNull();
        assertThat(colorInfo.getChildId()).isEqualTo(expectedDaughter.getChildId());
        assertThat(colorInfo.getColor()).isEqualTo("Blue");
        assertThat(colorInfo.getColorType()).isEqualTo(ColorType.HAIR_COLOR);
    }
}
