package com.example.swiggato.model;

import com.example.swiggato.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, message = "{javax.validation.constraints.Size.too_short}")
    @Size(max = 50, message = "{javax.validation.constraints.Size.too_long}")
    String name;

    @Column(unique = true, nullable = false)
    @Email
    String email;

    String adress;

    @Column(unique = true, nullable = false)
    @Size(min = 10, max = 10)
    String mobileNO;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;
}
