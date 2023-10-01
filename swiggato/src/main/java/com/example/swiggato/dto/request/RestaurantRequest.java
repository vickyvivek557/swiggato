package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {
    String name;

    String location;

    RestaurantCategory restaurantCategory;

    String contactNo;
}
