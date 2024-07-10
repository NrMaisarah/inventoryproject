package com.heroku.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.heroku.java.models.Petfood;
import com.heroku.repositories.PetfoodRepository;

import java.util.List;

@Service
public class PetfoodService {

    @Autowired
    private PetfoodRepository petfoodRepository;

    @Transactional
    public void createPetFood(Petfood petfood) {
        petfoodRepository.save(petfood);
    }

    public Petfood getPetFoodById(int inventoryId) {
        return petfoodRepository.findByInventoryId(inventoryId);
    }

    public List<Petfood> getAllPetFoods() {
        return petfoodRepository.findAllWithInventory();
    }

    @Transactional
    public void updatePetFood(Petfood petfood) {
        petfoodRepository.save(petfood);
    }

    @Transactional
    public void deletePetFood(int inventoryId) {
        petfoodRepository.deleteById(inventoryId);
    }
}