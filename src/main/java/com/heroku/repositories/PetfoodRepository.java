package com.heroku.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.heroku.java.models.Petfood;

public interface PetfoodRepository extends JpaRepository<Petfood, Integer> {

}
