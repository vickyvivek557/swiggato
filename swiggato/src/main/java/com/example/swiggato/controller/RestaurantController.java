package com.example.swiggato.controller;

import com.example.swiggato.dto.request.MenuRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    /**
     * constructor injection
     */
    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse response = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/change/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id){
        try{
            String message = restaurantService.changeOpenedStatus(id);
            return new ResponseEntity(message, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-menu")
    public ResponseEntity addMenu(@RequestBody MenuRequest menuRequest){
        RestaurantResponse response = restaurantService.addMenu(menuRequest);
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
