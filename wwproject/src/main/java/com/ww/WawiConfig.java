package com.ww;

import com.ww.child.*;
import com.ww.house.HouseRepository;
import com.ww.house.HouseService;
import com.ww.house.HouseServiceImpl;
import com.ww.person.PersonRepository;
import com.ww.person.PersonService;
import com.ww.person.PersonServiceImpl;
import com.ww.util.DataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
public class WawiConfig {

    @Bean
    public DataLoader dataLoader(HouseRepository houseRepository,
                                 PersonRepository personRepository,
                                 ChildRepository childRepository,
                                 MealRepository mealRepository) {
        return new DataLoader(houseRepository, personRepository, childRepository, mealRepository);
    }

    @Bean
    public PersonService personService(PersonRepository personRepository) {
        return new PersonServiceImpl(personRepository);
    }

    @Bean
    public HouseService houseService(HouseRepository houseRepository) {
        return new HouseServiceImpl(houseRepository);
    }

    @Bean
    public ChildService childService(ChildRepository childRepository) {
        return new ChildServiceImpl(childRepository);
    }
}
