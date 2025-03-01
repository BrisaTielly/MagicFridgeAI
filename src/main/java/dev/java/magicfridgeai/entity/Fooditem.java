package dev.java.magicfridgeai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "food_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fooditem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private FoodCategory foodCategory;

    private int quantity;

    private LocalDate expiryDate;
}
