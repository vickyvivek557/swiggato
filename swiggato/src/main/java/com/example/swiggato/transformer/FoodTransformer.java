package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.model.FoodItems;
import com.example.swiggato.model.MenuItem;

public class FoodTransformer {
    public static FoodItems FoodRequestToFoodItem(FoodRequest foodRequest, MenuItem menuItem){
        return FoodItems.builder()
                .menuItem(menuItem)
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(menuItem.getPrice() * foodRequest.getRequiredQuantity())
                .build();
    }

    public static FoodResponse FoodItemToFoodResponse(FoodItems foodItem){
        return FoodResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .price(foodItem.getMenuItem().getPrice())
                .category(foodItem.getMenuItem().getCategory())
                .veg(foodItem.getMenuItem().isVeg())
                .quantityAdded(foodItem.getRequiredQuantity())
                .build();
    }
}
