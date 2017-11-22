package com.ww.house;

import com.ww.WawiApplication;
import com.ww.person.Person;
import com.ww.util.DataLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WawiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class HouseServiceTest {
    @Autowired
    private HouseService houseService;

    @Autowired
    private DataLoader dataLoader;

    @Test
    public void whenRetrieveHouseByOwner_returnOwnedHouse() {
        Person samplePerson = dataLoader.createSamplePerson(1);
        House sampleHouse = dataLoader.createSampleHouse(1);
        House expectedHouse = dataLoader.addHouse(sampleHouse, samplePerson);
        House actualHouse = houseService.getHouseByOwner(1L);
        assertThat(actualHouse).isNotNull();
        assertThat(actualHouse).isEqualTo(expectedHouse);
    }
}
