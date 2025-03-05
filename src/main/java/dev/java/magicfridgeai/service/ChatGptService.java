package dev.java.magicfridgeai.service;

import dev.java.magicfridgeai.entity.Fooditem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {
    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

//    curl https://api.openai.com/v1/chat/completions \
//            -H "Content-Type: application/json" \
//            -H "Authorization: Bearer $OPENAI_API_KEY" \
//            -d '{
//            "model": "gpt-4o-mini",
//            "messages": [
//            {"role": "user", "content": "Say this is a test!"}
//            ],
//            "temperature": 0.7
//}'

    //Vai acessar a URL e vai fazer uma requisição direta para o chatgpt
    //É uma promessa de uma string(a string prometida no caso é a receita gerada)
    public Mono<String> generateRecipe(List<Fooditem> fooditems) {
        //Transformar a lista de alimentos em uma string bonitinha
        String food = fooditems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getName(), item.getFoodCategory(), item.getQuantity(), item.getExpiryDate()))
                .collect(Collectors.joining("/n"));
        //Vai sair tipo assim:
        //Maçã (Fruta) - Quantidade: 5, Validade: 10/03/2025
        //Leite (Laticínio) - Quantidade: 2, Validade: 05/03/2025
        //Arroz (Grão) - Quantidade: 1, Validade: 20/12/2025
        String prompt = "Baseado nos seguintes alimentos, sugira uma receita criativa e prática:\n" + food;

        //Fazendo o corpo da requisição
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        //Role: "system" → Define o comportamento geral do modelo.
                        Map.of("role", "system", "content", "Você é um assistente que cria receitas."),
                        //Role: "user" → Representa o que o usuário está perguntando ao ChatGPT.
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response ->
                        {
                            var choices = (List<Map<String, Object>>) response.get("choices");
                            if (choices != null && !choices.isEmpty()) {
                             Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                             return message.get("content").toString();
                            }else {
                                return "Nenhuma receita gerada";
                            }
                        }
                );
    }

}
