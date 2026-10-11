package com.morri.foodinventory.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.morri.foodinventory.model.Food;
import com.morri.foodinventory.repository.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Integer foodId) {
        return foodRepository.findById(foodId);
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Optional<Food> updateFood(Integer foodId, Food updatedFood) {
        return foodRepository.findById(foodId)
                .map(existingFood -> {
                    existingFood.setName(updatedFood.getName());
                    existingFood.setCategoryId(updatedFood.getCategoryId());
                    existingFood.setPreparationState(updatedFood.getPreparationState());
                    existingFood.setCaloriesPer100g(updatedFood.getCaloriesPer100g());
                    existingFood.setProteinPer100g(updatedFood.getProteinPer100g());
                    existingFood.setCarbsPer100g(updatedFood.getCarbsPer100g());
                    existingFood.setFatPer100g(updatedFood.getFatPer100g());

                    return foodRepository.save(existingFood);
                });
    }

    public boolean deleteFood(Integer foodId) {
        // public: FoodController can call this method.
        // boolean: the method returns either true or false.
        // foodId: identifies which food should be deleted.

        if (!foodRepository.existsById(foodId)) {
            // existsById checks whether the record exists.
            // ! means "not."
            // Therefore: if the food does not exist...

            return false;
            // Tell the controller that nothing was deleted.
        }

        foodRepository.deleteById(foodId);
        // The food exists, so tell the repository to delete it.

        return true;
        // Tell the controller that the deletion succeeded.
    }
    
}

