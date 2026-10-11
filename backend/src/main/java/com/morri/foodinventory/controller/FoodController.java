package com.morri.foodinventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.morri.foodinventory.model.Food;
import com.morri.foodinventory.service.FoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<Food> getFoodById(
            @PathVariable Integer foodId
    ) {
        Optional<Food> food = foodService.getFoodById(foodId);

        if (food.isPresent()) {
            return ResponseEntity.ok(food.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodService.createFood(food);
    }

    @PutMapping("/{foodId}") // Handles PUT requests such as /api/foods/1
    public ResponseEntity<Food> updateFood(

            @PathVariable Integer foodId,
            // Takes the ID from the URL.
            // /api/foods/1 means foodId = 1

            @RequestBody Food updatedFood
            // Converts the request's JSON into a Food object.

    ) {
        Optional<Food> food =
                foodService.updateFood(foodId, updatedFood);
        // Sends the ID and new values to FoodService.
        // The service searches for the existing food,
        // copies the new values and saves the changes.

        if (food.isPresent()) {
            // Checks whether FoodService found and updated the food.

            return ResponseEntity.ok(food.get());
            // food.get() retrieves the updated Food from Optional.
            // ResponseEntity.ok() returns HTTP 200 with the updated food as JSON.
        }

        return ResponseEntity.notFound().build();
        // Runs when the requested food ID does not exist.
        // Returns HTTP 404 with no response body.
    }

    @DeleteMapping("/{foodId}") // Receives DELETE /api/foods/{foodId}
    public ResponseEntity<Void> deleteFood(
            @PathVariable Integer foodId) { // Takes foodId from the URL

        boolean deleted =
                foodService.deleteFood(foodId); // Asks the service to delete it

        if (deleted) { // The service returned true
            return ResponseEntity
                    .noContent()
                    .build(); // Return 204: deletion succeeded
        }

        return ResponseEntity
                .notFound()
                .build(); // Return 404: that food wasn't found
    }
}