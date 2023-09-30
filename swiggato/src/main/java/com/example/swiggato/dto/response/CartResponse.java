package com.example.swiggato.dto.response;

import com.example.swiggato.model.MenuItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    double cartTotal;

    List<MenuItem> foodItems;
}
