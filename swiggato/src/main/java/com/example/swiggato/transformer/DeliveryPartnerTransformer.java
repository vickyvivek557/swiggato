package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.model.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {
    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest request){
        return DeliveryPartner.builder()
                .name(request.getName())
                .gender(request.getGender())
                .mobileNO(request.getMobileNo())
                .orders(new ArrayList<>())
                .build();
    }
}
