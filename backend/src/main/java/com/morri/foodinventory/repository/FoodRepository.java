package com.morri.foodinventory.repository;

import com.morri.foodinventory.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
