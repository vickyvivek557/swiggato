package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CartResponse;
import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .mobileNo(customerRequest.getMobileNo())
                .adress(customerRequest.getAdress())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());
        return CustomerResponse.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .adress(customer.getAdress())
                .email(customer.getEmail())
                .cart(cartResponse)
                .build();
    }
}
