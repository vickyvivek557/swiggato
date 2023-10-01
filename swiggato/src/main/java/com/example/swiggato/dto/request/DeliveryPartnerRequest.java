package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPartnerRequest {
    String name;

    String mobileNo;

    Gender gender;
}
