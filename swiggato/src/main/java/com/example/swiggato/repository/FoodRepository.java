package com.example.swiggato.repository;

import com.example.swiggato.model.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodItems, Integer> {
}
