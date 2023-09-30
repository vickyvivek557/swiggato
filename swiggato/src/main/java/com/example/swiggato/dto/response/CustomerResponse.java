package com.example.swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    String name;

    String mobileNo;

    String email;

    String adress;

    CartResponse cart;
}
