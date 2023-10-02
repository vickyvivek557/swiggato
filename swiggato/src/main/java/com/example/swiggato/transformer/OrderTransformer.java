package com.example.swiggato.transformer;

import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.dto.response.OrderResponse;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.FoodItems;
import com.example.swiggato.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity PrepareOrderFromCart(Cart cart){
        return OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(OrderEntity order){
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItems foodItem : order.getFoodItems()){
            FoodResponse foodResponse = FoodTransformer.FoodItemToFoodResponse(foodItem);
            foodResponseList.add(foodResponse);
        }
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .orderTotal(order.getOrderTotal())
                .orderTime(order.getOrderTime())
                .customeName(order.getCustomer().getName())
                .customerMobile(order.getCustomer().getMobileNo())
                .deliveryPartnerName(order.getDeliveryPartner().getName())
                .deliveryPartnerMobile(order.getDeliveryPartner().getMobileNO())
                .restaurantName(order.getRestaurant().getName())
                .foodResponses(foodResponseList)
                .build();
    }
}
