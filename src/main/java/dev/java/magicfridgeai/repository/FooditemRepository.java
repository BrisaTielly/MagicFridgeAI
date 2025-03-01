package dev.java.magicfridgeai.repository;

import dev.java.magicfridgeai.entity.Fooditem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooditemRepository extends JpaRepository<Fooditem, Long> {
}
