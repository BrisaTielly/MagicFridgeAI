package dev.java.magicfridgeai.controller;

import dev.java.magicfridgeai.entity.Fooditem;
import dev.java.magicfridgeai.service.ChatGptService;
import dev.java.magicfridgeai.service.FooditemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RecipeController {
//Precisa de um endpoint que, toda vez que eu bater nele, vai ter um método que vai ver tudo que tem no meu banco de dados, jogar pro chatgpt
    private ChatGptService chatGptService;
    private FooditemService fooditemService;

    public RecipeController(ChatGptService chatGptService, FooditemService fooditemService) {
        this.chatGptService = chatGptService;
        this.fooditemService = fooditemService;
    }

    //Mono: Representa um valor único ou vazio (0 ou 1 elemento), por exemplo, uma resposta de uma requisição HTTP(Um único objeto JSON, tipo busca por id).
    //Flux: Representa um fluxo de múltiplos valores (1 ou mais). Por exemplo, uma lista de itens retornada por uma API.
    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        List<Fooditem> all = fooditemService.findAll();
        return chatGptService.generateRecipe(all)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
