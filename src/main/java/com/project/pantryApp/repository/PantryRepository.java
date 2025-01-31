package com.project.pantryApp.repository;

import com.project.pantryApp.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PantryRepository extends JpaRepository<Food,String> {

   Optional<List<Food>> findByProductNameContainingIgnoreCase(String itemName);

   Optional<Food> findByBarcode(String itemCode);
//
//    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
//    List<foodDao> findByProductName(@Param("productName") String productName);

   Optional<Food> findById(String itemID);

}
