package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.MenuRequest;
import com.example.swiggato.dto.response.MenuResponse;
import com.example.swiggato.model.MenuItem;

public class MenuTransformer {
    public static MenuItem MenuRequestToMenuItem(MenuRequest menuRequest){
        return MenuItem.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .category(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse MenuItemToMenuResponse(MenuItem menuItem){
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .price(menuItem.getPrice())
                .category(menuItem.getCategory())
                .veg(menuItem.isVeg())
                .build();
    }
}
