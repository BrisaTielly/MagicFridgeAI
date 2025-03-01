package dev.java.magicfridgeai.repository;

import dev.java.magicfridgeai.entity.Fooditem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooditemRepository extends JpaRepository<Fooditem, Long> {
}
