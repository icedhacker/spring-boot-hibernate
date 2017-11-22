package com.ww.house;

import com.ww.person.Person;

public class HouseServiceImpl implements HouseService {

    private HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public House getHouseByOwner(Long ownerId) {
        Person owner = new Person();
        owner.setPersonId(ownerId);
        return houseRepository.findByOwner(owner);
    }
}
