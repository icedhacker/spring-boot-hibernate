package com.ww.house;

import com.ww.person.Person;
import com.ww.person.PersonService;

public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;
    private PersonService personService;

    public HouseServiceImpl(HouseRepository houseRepository, PersonService personService) {
        this.houseRepository = houseRepository;
        this.personService = personService;
    }

    @Override
    public House getHouseByOwner(Long ownerId) {
        Person owner = personService.getPersonById(ownerId);
        if (owner != null) {
            return houseRepository.findByOwner(owner);
        }
        return null;
    }
}
