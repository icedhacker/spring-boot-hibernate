package com.ww;

import com.ww.child.ChildService;
import com.ww.person.PersonService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJob {

    private ChildService childService;

    private PersonService personService;

    public SchedulerJob(ChildService childService, PersonService personService) {
        this.childService = childService;
        this.personService = personService;
    }

    @Scheduled(fixedRateString = "${schedule.interval}")
    public void generateParentSummary() {
        long personsCount = personService.countPersons();
        childService.generateParentSummary(personsCount);
    }
}
