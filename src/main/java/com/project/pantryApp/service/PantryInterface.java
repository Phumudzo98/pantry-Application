package com.project.pantryApp.service;

import com.project.pantryApp.dao.FoodDao;
import com.project.pantryApp.model.Food;

import java.util.List;

public interface PantryInterface {

    public boolean addItem(FoodDao uploadFood);
    public boolean removeItem(String itemID);
    public List<Food> searchItem(String itemName);
    public List<Food> displayItems();
}
