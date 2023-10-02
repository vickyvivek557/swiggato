package com.example.swiggato.service;

import com.example.swiggato.dto.response.OrderResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.exception.EmptyCartException;
import com.example.swiggato.model.*;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.DeliveryPartnerRepository;
import com.example.swiggato.repository.OrderRepository;
import com.example.swiggato.repository.RestaurantRepository;
import com.example.swiggato.transformer.OrderTransformer;
import com.example.swiggato.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    final OrderRepository orderRepository;
    final ValidationUtils validationUtils;
    final CustomerRepository customerRepository;
    final DeliveryPartnerRepository deliveryPartnerRepository;
    final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ValidationUtils validationUtils,
                        CustomerRepository customerRepository,
                        DeliveryPartnerRepository deliveryPartnerRepository,
                        RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.validationUtils = validationUtils;
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse placeOrder(String customerMobile) {
        //check, if customer exists or not
        if(!validationUtils.validateCustomerMobile(customerMobile)){
            throw new CustomerNotFoundException("Invalid Customer mobile number !!!");
        }
        Customer customer = customerRepository.findByMobileNo(customerMobile);

        //verify if cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().size() == 0){
            throw new EmptyCartException("Sorry, your cart is empty !!!");
        }

        //find delivery Partner (Randomly) to deliver order.
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        //prepare order from cart items
        OrderEntity order = OrderTransformer.PrepareOrderFromCart(cart);

        //save order entity
        OrderEntity savedOrder = orderRepository.save(order);

        //set relationship between order & other entities
        savedOrder.setCustomer(customer);
        savedOrder.setDeliveryPartner(deliveryPartner);
        savedOrder.setRestaurant(restaurant);
        savedOrder.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        //now foodItems will belong to order, not to any cart
        for(FoodItems foodItem : cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }

        //now clear the cart, because after placing order cart should be empty now
        cart.setFoodItems(new ArrayList<>());
        cart.setCartTotal(0);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        restaurantRepository.save(restaurant);

        //prepare order response and return
        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }
}
