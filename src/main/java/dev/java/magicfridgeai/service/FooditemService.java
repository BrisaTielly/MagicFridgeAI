package dev.java.magicfridgeai.service;

import dev.java.magicfridgeai.entity.Fooditem;
import dev.java.magicfridgeai.repository.FooditemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FooditemService {
    private final FooditemRepository fooditemRepository;

    public FooditemService(FooditemRepository fooditemRepository) {
        this.fooditemRepository = fooditemRepository;
    }

    public Fooditem save(Fooditem fooditem){
        return fooditemRepository.save(fooditem);
    }

    public Optional<Fooditem> findById(Long id){
        return fooditemRepository.findById(id);
    }

    public List<Fooditem> findAll(){
        return fooditemRepository.findAll();
    }

    public Fooditem update(Long id, Fooditem fooditem){
        Optional<Fooditem> oldItem = fooditemRepository.findById(id);
        if (oldItem.isPresent()){
            fooditem.setId(id);
            return fooditemRepository.save(fooditem);
        }
        return null;
    }

    public void delete(Long id){
        fooditemRepository.deleteById(id);
    }

}
