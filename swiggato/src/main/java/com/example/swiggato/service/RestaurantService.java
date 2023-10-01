package com.example.swiggato.service;

import com.example.swiggato.dto.request.MenuRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.exception.RestaurantNotFoundException;
import com.example.swiggato.model.MenuItem;
import com.example.swiggato.model.Restaurant;
import com.example.swiggato.repository.RestaurantRepository;
import com.example.swiggato.transformer.MenuTransformer;
import com.example.swiggato.transformer.RestaurantTransformer;
import com.example.swiggato.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RestaurantService {
    /**
     * constructor injection
     */
    final RestaurantRepository restaurantRepository;
    final ValidationUtils validationUtils;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        //dto to model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        restaurant.setContactNumber(restaurantRequest.getContactNo());

        //save
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        //model to response dto
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public String changeOpenedStatus(int id) {
        //check if id is valid or not
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Invalid Restaurant Id !!!");
        }

        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        if(savedRestaurant.isOpened()){
            return "Restaurant is opende now.";
        }
        return "Restaurant is closed now.";
    }

    public RestaurantResponse addMenu(MenuRequest menuRequest) {
        //check if id is valid or not
        if(!validationUtils.validateRestaurantId(menuRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("Restaurant Does not Exist !!!");
        }

        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestaurantId()).get();
        MenuItem menuItem = MenuTransformer.MenuRequestToMenuItem(menuRequest);
        menuItem.setRestaurant(restaurant);
        restaurant.getAvailableMenuItems().add(menuItem);

        //save restaurant and menuItem both
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        //prepare response and return
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }
}