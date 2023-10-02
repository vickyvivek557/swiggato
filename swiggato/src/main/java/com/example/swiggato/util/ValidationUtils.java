package com.example.swiggato.util;

import com.example.swiggato.model.Customer;
import com.example.swiggato.model.Restaurant;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final RestaurantRepository restaurantRepository;
    final CustomerRepository customerRepository;

    @Autowired
    public ValidationUtils(RestaurantRepository restaurantRepository,
                           CustomerRepository customerRepository) {
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
    }

    public boolean validateRestaurantId(int id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.isPresent();
    }

    public boolean validateCustomerMobile(String mobile){
        Customer customer = customerRepository.findByMobileNo(mobile);
        return customer != null;
    }
}
