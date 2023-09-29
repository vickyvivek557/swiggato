package com.example.swiggato.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Data
@Table(name = "order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderId; //UUID

    double orderTotal;

    @CreationTimestamp
    Date orderTime;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<FoodItems> foodItems = new ArrayList<>();
}
