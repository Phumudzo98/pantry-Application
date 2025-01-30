package com.project.pantryApp.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDao {

    private String productName;
    private int quantity;
    private LocalDate expiriyDate;
    private String category;
    private String weight_litres;
    private String barcode;
}
