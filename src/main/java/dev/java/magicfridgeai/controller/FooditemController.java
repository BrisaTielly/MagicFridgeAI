package dev.java.magicfridgeai.controller;

import dev.java.magicfridgeai.entity.Fooditem;
import dev.java.magicfridgeai.service.FooditemService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FooditemController {
    private final FooditemService fooditemService;

    public FooditemController(FooditemService fooditemService) {
        this.fooditemService = fooditemService;
    }

    @PostMapping("/save")
    public ResponseEntity<Fooditem> save (@RequestBody Fooditem fooditem){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fooditemService.save(fooditem));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Fooditem> findById(@PathVariable Long id){
        return ResponseEntity.ok(fooditemService.findById(id).get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Fooditem>> findAll(){
        return ResponseEntity.ok(fooditemService.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fooditem fooditem){
        Fooditem update = fooditemService.update(id, fooditem);
        if(update != null){
            return ResponseEntity.ok(update);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return  ResponseEntity.ok("Deletado com sucesso");
    }
}
