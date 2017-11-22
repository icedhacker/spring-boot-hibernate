package com.ww.person;

import com.ww.WawiApplication;
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
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Autowired
    private DataLoader dataLoader;

    @Test
    public void whenRetrievePersonById_returnPerson() {
        Person samplePerson = dataLoader.createSamplePerson(1);
        samplePerson = dataLoader.addPerson(samplePerson);
        Person actualPerson = personService.getPersonById(1L);
        assertThat(actualPerson).isNotNull();
        assertThat(actualPerson).isEqualTo(samplePerson);
    }
}
