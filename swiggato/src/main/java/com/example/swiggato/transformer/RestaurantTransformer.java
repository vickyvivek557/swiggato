package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.MenuResponse;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {
    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest request){
        return Restaurant.builder()
                .name(request.getName())
                .location(request.getLocation())
                .restaurantCategory(request.getRestaurantCategory())
                .contactNumber(request.getContactNo())
                .opened(true)
                .availableMenuItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){
        List<MenuResponse> menu = restaurant.getAvailableMenuItems()
                .stream()
                .map(menuItem -> MenuTransformer.MenuItemToMenuResponse(menuItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .contactNo(restaurant.getContactNumber())
                .location(restaurant.getLocation())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}
