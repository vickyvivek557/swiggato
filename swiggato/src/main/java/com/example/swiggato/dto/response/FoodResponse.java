package com.example.swiggato.dto.response;

import com.example.swiggato.Enum.FoodCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodResponse {
    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    int quantityAdded;
}
