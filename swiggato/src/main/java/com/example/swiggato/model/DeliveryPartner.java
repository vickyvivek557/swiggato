package com.example.swiggato.model;

import com.example.swiggato.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Data
@Table(name = "delivery_partner")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, max = 50)
    String name;

    @Column(unique = true, nullable = false)
    @Size(min = 10, max = 10)
    String mobileNO;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
