package dev.java.magicfridgeai.controller;

import dev.java.magicfridgeai.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {
//Precisa de um endpoint que, toda vez que eu bater nele, vai ter um método que vai ver tudo que tem no meu banco de dados, jogar pro chatgpt
    private ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    //Mono: Representa um valor único ou vazio (0 ou 1 elemento), por exemplo, uma resposta de uma requisição HTTP(Um único objeto JSON, tipo busca por id).
    //Flux: Representa um fluxo de múltiplos valores (1 ou mais). Por exemplo, uma lista de itens retornada por uma API.
    @GetMapping
    public Mono<ResponseEntity<String>> generateRecipe(){
        //A conexão ainda vai estar ativa enquanto eu espero o chatgpt me responder, como uma janela aberta
    }
}
