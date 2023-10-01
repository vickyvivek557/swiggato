package com.example.swiggato.dto.response;

import com.example.swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    String dishName;

    double price;

    FoodCategory category;

    boolean veg;
}
