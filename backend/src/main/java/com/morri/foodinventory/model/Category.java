package com.morri.foodinventory.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer CategoryId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    //empty or no-argument constructor
    public Category() { 
    }   

    //constructor that accepts a category name
    public Category(String name) {
        this.name = name;
    }

    // Getters and setters
}