package com.example.swiggato.dto.response;

import com.example.swiggato.model.Customer;
import com.example.swiggato.model.DeliveryPartner;
import com.example.swiggato.model.Restaurant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

public class OrderResponse {

    String orderId; //UUID

    double orderTotal;

    Date orderTime;

    String customeName;

    String customerMobile;

    String deliveryPartnerName;

    String deliveryPartnerMobile;

    String restaurantName;

    List<FoodResponse> foodResponses;

}
