package com.example.swiggato.util;

import com.example.swiggato.model.Restaurant;
import com.example.swiggato.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final RestaurantRepository restaurantRepository;

    @Autowired
    public ValidationUtils(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean validateRestaurantId(int id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.isPresent();
    }
}
