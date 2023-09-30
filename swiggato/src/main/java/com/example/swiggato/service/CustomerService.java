package com.example.swiggato.service;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.Customer;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //customer request to customer
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allot a cart to the customer
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        //save the customer to repository
        Customer savedCustomer = customerRepository.save(customer);

        //saved customer to customer response and return
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse findCustomerByMobile(String mobile) {
        Customer customer = customerRepository.findByMobileNo(mobile);
        if(customer == null){
            throw new CustomerNotFoundException("Invalid mobile number !!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
