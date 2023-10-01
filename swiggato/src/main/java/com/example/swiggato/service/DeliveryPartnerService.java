package com.example.swiggato.service;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.model.DeliveryPartner;
import com.example.swiggato.repository.DeliveryPartnerRepository;
import com.example.swiggato.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {
    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    public String addDeliveryPartner(DeliveryPartnerRequest partnerRequest) {
        //dto to model
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer
                .DeliveryPartnerRequestToDeliveryPartner(partnerRequest);

        //save
        DeliveryPartner savedPartner = deliveryPartnerRepository.save(deliveryPartner);

        return "You have been successfully registered as Delivery Partner.";
    }
}
