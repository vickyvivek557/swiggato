package com.example.swiggato.controller;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-food")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodRequest foodRequest){
        try {
            CartStatusResponse response = cartService.addFoodItemToCart(foodRequest);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
