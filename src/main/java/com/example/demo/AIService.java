package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create();

    public String callAI(String input) {

        // Dummy API call (you can replace with real Gemini later)
        return webClient.post()
                .uri("https://example.com/api?key=" + apiKey)
                .bodyValue(input)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("AI Error - Try again")
                .block();
    }
}