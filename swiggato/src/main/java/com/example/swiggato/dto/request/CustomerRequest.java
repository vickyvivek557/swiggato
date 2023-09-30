package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    String name;

    String email;

    String adress;

    String mobileNo;

    Gender gender;
}
