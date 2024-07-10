package com.heroku.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.heroku.java.models.Petfood;

public interface PetfoodRepository extends JpaRepository<Petfood, Integer> {

}

package com.heroku.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.heroku.java.models.Petfood;

import java.util.List;

@Repository
public interface PetfoodRepository extends JpaRepository<Petfood, Integer> {

    @Query("SELECT p FROM Petfood p JOIN FETCH p.inventory ORDER BY p.inventory.inventoryID")
    List<Petfood> findAllWithInventory();

    @Query("SELECT p FROM Petfood p JOIN FETCH p.inventory WHERE p.inventory.inventoryID = :inventoryId")
    Petfood findByInventoryId(int inventoryId);

    @Query("DELETE FROM inventory WHERE inventoryID = ?")
    Petfood deleteById(int inventoryId);

    
}
  