package com.example.swiggato.service;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.exception.MenuItemNotFoundException;
import com.example.swiggato.exception.RestaurantClosedException;
import com.example.swiggato.model.*;
import com.example.swiggato.repository.CartRepository;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.FoodRepository;
import com.example.swiggato.repository.MenuItemRepository;
import com.example.swiggato.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    final CartRepository cartRepository;
    final CustomerRepository customerRepository;
    final MenuItemRepository menuItemRepository;
    final FoodRepository foodRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       CustomerRepository customerRepository,
                       MenuItemRepository menuItemRepository,
                       FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.foodRepository = foodRepository;
    }

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist.");
        }

        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(foodRequest.getMenuItemId());
        if(optionalMenuItem.isEmpty()){
            throw new MenuItemNotFoundException("Item not available in the restaurant !!!");
        }

        MenuItem menuItem = optionalMenuItem.get();
        if(!menuItem.isAvailable()){
            throw new MenuItemNotFoundException("Given dish is out of stock for now !!!");
        }
        if(!menuItem.getRestaurant().isOpened()){
            throw new RestaurantClosedException("The Restaurant is closed now !!!");
        }

        Cart cart = customer.getCart();

        /**
         * check, if items are from same restaurant or not
         * if current and new restaurants are not equal then clear the cart, and add new foodItem in it
         */

        if(cart.getFoodItems().size() != 0){
            Restaurant currentRestaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();
            Restaurant newRestaurant = menuItem.getRestaurant();

            //now, if current and new restaurants are not equal then clear the cart
            if(!currentRestaurant.equals(newRestaurant)){
                List<FoodItems> foodItems = cart.getFoodItems();
                for(FoodItems foodItem: foodItems){
                    foodItem.setCart(null);
                    foodItem.setMenuItem(null);
                    foodItem.setOrder(null);
                    foodRepository.delete(foodItem);
                }
                cart.setFoodItems(new ArrayList<>());
                cart.setCartTotal(0);
            }
        }
        /**
         * now check, if foodItem already exist in the cart or not.
         * if already exists then increase the quantity in the cart.
         * if not exist, then add it as a new foodItem in the cart
         */

        boolean foodItemAlreadyExists = false;
        FoodItems savedFoodItem = null;
        if(cart.getFoodItems().size() != 0){
            for(FoodItems foodItem : cart.getFoodItems()){
                if(foodItem.getMenuItem().getId() == menuItem.getId()){
                    foodItemAlreadyExists = true;
                    savedFoodItem = foodItem;
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr + foodRequest.getRequiredQuantity());
                    break;
                }
            }
        }

        //if foodItem already not present in cart then create it and add to the cart
        if(!foodItemAlreadyExists){
            FoodItems foodItem = FoodTransformer.FoodRequestToFoodItem(foodRequest, menuItem);
            savedFoodItem = foodRepository.save(foodItem);

            cart.getFoodItems().add(savedFoodItem);
            menuItem.getFoodItems().add(savedFoodItem);
            savedFoodItem.setCart(cart);
        }

        double cartTotal = 0;
        for(FoodItems food : cart.getFoodItems()){
            cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
        }
        cart.setCartTotal(cartTotal);

        //save
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenu = menuItemRepository.save(menuItem);

        //prepare foodResponse list
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItems foodItem : cart.getFoodItems()){
            FoodResponse foodResponse = FoodTransformer.FoodItemToFoodResponse(foodItem);
            foodResponseList.add(foodResponse);
        }

        return CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAdress())
                .restaurantName(savedMenu.getRestaurant().getName())
                .foodList(foodResponseList)
                .cartTotal(savedCart.getCartTotal())
                .build();
    }
}
