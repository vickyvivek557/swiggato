package com.example.swiggato.dto.response;

import com.example.swiggato.model.MenuItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {
    String name;

    String contactNo;

    String location;

    boolean opened;

    List<MenuResponse> menu;
}
