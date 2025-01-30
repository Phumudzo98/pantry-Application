package com.project.pantryApp.controller;


import com.project.pantryApp.dao.FoodDao;
import com.project.pantryApp.model.Food;
import com.project.pantryApp.service.PantryInterface;
import com.project.pantryApp.service.implementation.PantryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PantryController {



//    @Autowired
//    private final PantryImplementation PantryImpl;

    @Autowired
    private PantryInterface pantryInterface;

//    public PantryController(PantryImplementation pantryImpl) {
//        PantryImpl = pantryImpl;
//    }


    @PostMapping(value="/upload-pantry")
    public ResponseEntity<String> addItem(@RequestBody List<FoodDao> uploadFood)
    {

        if(uploadFood!=null)
        {
            for(FoodDao item: uploadFood) {
                pantryInterface.addItem(item);
            }

            return ResponseEntity.ok("Item(s) added into pantry");
        }

        return ResponseEntity.ok("Something went wrong. Please check your entries again.");

    }

    @GetMapping(value="/removeItem/{itemID}")
    public ResponseEntity<String> removeItem(@PathVariable("itemID") String itemID)
    {

        boolean result = pantryInterface.removeItem(itemID);

        if(result)
        {
            return ResponseEntity.ok("Item has been deleted");
        }

        return ResponseEntity.ok("Something went wrong.");
    }

    @GetMapping(value="/searchItem/{itemDetails}")
    public ResponseEntity<List<Food>> searchItem(@PathVariable("itemDetails") String itemDetails)
    {

        List<Food> results = pantryInterface.searchItem(itemDetails);

        if(results.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(results);

    }


    @GetMapping(value = "/get-all-items")
    public ResponseEntity<List<Food>> getAllItems()
    {

        List<Food> foodList = pantryInterface.displayItems();

        if(foodList!=null)
        {
            return ResponseEntity.ok(foodList);
        }
        else
        {
            return ResponseEntity.noContent().build();
        }
    }


}
