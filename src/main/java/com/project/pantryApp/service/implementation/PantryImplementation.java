package com.project.pantryApp.service.implementation;

import com.project.pantryApp.dao.FoodDao;
import com.project.pantryApp.model.Food;
import com.project.pantryApp.repository.PantryRepository;
import com.project.pantryApp.service.PantryInterface;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
@Service
public class PantryImplementation implements PantryInterface {




    @Autowired
    PantryRepository PantryRepo;

    @Override
    @Transactional
    public boolean addItem(FoodDao uploadFood) {

        Food foodList;

        Optional<Food> optionalFood = PantryRepo.findByBarcode(uploadFood.getBarcode());

        if(optionalFood.isPresent())
        {
            foodList=optionalFood.get();

            if(foodList.getBarcode().equals(uploadFood.getBarcode()) &&
                    foodList.getExpiriyDate().equals(uploadFood.getExpiriyDate()))
            {
                foodList.setQuantity(foodList.getQuantity()+ uploadFood.getQuantity());

                PantryRepo.save(foodList);

                return true;
            }


        }

        Food storeFood = Food.builder()
                .category(uploadFood.getCategory())
                .expiriyDate(uploadFood.getExpiriyDate())
                .productName(uploadFood.getProductName())
                .weight_litres(uploadFood.getWeight_litres())
                .quantity(uploadFood.getQuantity())
                .barcode(uploadFood.getBarcode())
                .build();


        PantryRepo.saveAndFlush(storeFood);

        return false;
    }

    @Override
    public boolean removeItem(String itemID) {

        Food item;

        Optional<Food> optionalFood = PantryRepo.findById(itemID);

        if(optionalFood.isPresent())
        {
            item=optionalFood.get();

            PantryRepo.delete(item);

            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public List<Food> searchItem(String productName) {

        List<Food> foodList;
        Optional<List<Food>> optionalFoods = PantryRepo.findByProductNameContainingIgnoreCase(productName);

        if(optionalFoods.isPresent())
        {
            foodList=optionalFoods.get();

            return foodList;
        }

        return Collections.emptyList();
    }

    @Override
    public List<Food> displayItems() {


        List<Food> foodList;

        foodList = PantryRepo.findAll();

        return foodList;
    }


}
