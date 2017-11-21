package com.ww.util;

import com.ww.child.Child;
import com.ww.child.ChildRepository;
import com.ww.child.Meal;
import com.ww.child.MealRepository;
import com.ww.house.House;
import com.ww.house.HouseRepository;
import com.ww.house.HouseType;
import com.ww.person.Person;
import com.ww.person.PersonRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public final class DataLoader {
    private HouseRepository houseRepository;
    private PersonRepository personRepository;
    private ChildRepository childRepository;
    private MealRepository mealRepository;
    private Random r = new Random();

    public DataLoader(HouseRepository houseRepository,
                      PersonRepository personRepository,
                      ChildRepository childRepository,
                      MealRepository mealRepository) {
        this.houseRepository = houseRepository;
        this.personRepository = personRepository;
        this.childRepository = childRepository;
        this.mealRepository = mealRepository;
    }

    public List<Person> loadPersons(int count) {
        // Create {count} persons in the db.
        List<Person> personList = new ArrayList<>();
        IntStream.range(0, count).parallel().forEach(
                i -> personList.add(createSamplePerson(i)));
        List<Person> persons = personRepository.save(personList);
        log.info("{} persons loaded.", persons.size());
        personRepository.flush();
        return persons;
    }

    public List<House> loadHouses(int count) {
        List<Person> persons = loadPersons(count);

        // Create {count} houses in the db.
        List<House> houseList = new ArrayList<>();
        IntStream.range(0, count).parallel().forEach(
                i -> {
                    House house = createSampleHouse(i);
                    house.setOwner(persons.get(i));
                    houseList.add(house);
                }
        );
        List<House> houses = houseRepository.save(houseList);
        log.info("{} houses loaded.", houses.size());
        houseRepository.flush();
        return houses;
    }

    public List<Child> loadChildren(int count) {
        List<Person> persons = loadPersons(count);

        // Create {count*2} children in the db.
        List<Child> childList = new ArrayList<>();
        IntStream.range(0, count).parallel().forEach(
                i -> {
                    Child child = createSampleChild(i * 2);
                    child.setParent(persons.get(i));
                    childList.add(child);

                    Child child2 = createSampleChild((i * 2) + 1);
                    child2.setParent(persons.get(i));
                    childList.add(child2);
                }
        );
        List<Child> children = childRepository.save(childList);
        log.info("{} children loaded.", children.size());
        childRepository.flush();
        return children;
    }

    public List<Meal> loadMeals(int count) {
        List<Child> children = loadChildren(count);

        // Create {count*6} meals
        List<Meal> mealList = new ArrayList<>();
        IntStream.range(0, count * 2).parallel().forEach(
                i -> {
                    Meal meal = createSampleMeal(i * 3);
                    meal.setChild(children.get(i));
                    mealList.add(meal);

                    Meal meal2 = createSampleMeal((i * 3) + 1);
                    meal2.setChild(children.get(i));
                    mealList.add(meal2);

                    Meal meal3 = createSampleMeal((i * 3) + 2);
                    meal3.setChild(children.get(i));
                    mealList.add(meal3);
                }
        );
        List<Meal> meals = mealRepository.save(mealList);
        log.info("{} meals loaded.", meals.size());
        mealRepository.flush();
        return meals;
    }

    public House addHouse(House house, Person person) {
        Person owner = personRepository.save(person);
        house.setOwner(owner);
        return houseRepository.save(house);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Meal> addMeals(Person person, Child child, List<Meal> meals) {
        Person parent = personRepository.save(person);
        child.setParent(parent);
        Child mealChild = childRepository.save(child);
        for (Meal meal : meals) {
            meal.setChild(mealChild);
        }
        return mealRepository.save(meals);
    }

    public Person createSamplePerson(int i) {
        Person person = new Person();
        person.setName("Test Person" + i);
        person.setAge(r.nextInt(50) + 20);
        return person;
    }

    public House createSampleHouse(int i) {
        House house = new House();
        house.setAddress("Test Address" + i);
        house.setHouseType(HouseType.values()[r.nextInt(2)]);
        house.setZipCode("TESTZIP" + i);
        return house;
    }

    public Child createSampleChild(int i) {
        Child child = new Child();
        child.setName("Test Child" + i);
        child.setAge(r.nextInt(15) + 1);
        return child;
    }

    public Meal createSampleMeal(int i) {
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        long randomDay = (long) minDay + r.nextInt(maxDay - minDay);
        LocalDate inventedDate = LocalDate.ofEpochDay(randomDay);
        Meal meal = new Meal();
        meal.setName("Test Meal" + i);
        meal.setInventedDate(Date.from(inventedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return meal;
    }
}
