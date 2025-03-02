package dev.java.magicfridgeai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // A anotação @Value é usada para injetar a URL da API do ChatGPT a partir do arquivo de configuração (application.properties).Se a propriedade chatgpt.api.url não estiver definida, o valor padrão será https://api.openai.com/v1/chat/completions.
    @Value("${chatgpt.api.url:https://api.openai.com/v1/chat/completions}")
    private String chatGptApiUrl;

    //O WebClient é uma ferramenta do Spring para fazer chamadas HTTP (como GET, POST, PUT, DELETE) para APIs externas ou serviços web. Ele é moderno, eficiente e foi criado para substituir o antigo RestTemplate.

    //Aplicação de forma configurada
    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl(chatGptApiUrl).build(); //Url base para todas as requisições
        //Retorna uma instância do WebClient configurada com uma url base.
    }
}
