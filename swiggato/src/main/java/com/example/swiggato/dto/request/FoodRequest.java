package com.example.swiggato.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodRequest {

    String customerMobile;

    int menuItemId;

    int requiredQuantity;
}
