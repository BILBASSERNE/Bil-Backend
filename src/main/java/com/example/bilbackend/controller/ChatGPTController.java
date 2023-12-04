package com.example.bilbackend.controller;

import com.example.bilbackend.ChatGptDTO.ChatMessage;
import com.example.bilbackend.ChatGptDTO.ChatRequest;
import com.example.bilbackend.ChatGptDTO.ChatResponse;
import com.example.bilbackend.ChatGptDTO.Choice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ChatGPTController {

    private final WebClient webClient;

    public ChatGPTController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }


    @GetMapping("/chat/{request}")
    public ResponseEntity<List<Choice>> chatWithGPT(@PathVariable String request) {
        try {
            System.out.println(request);

            ChatRequest chatRequest = new ChatRequest();
            chatRequest.setModel("gpt-3.5-turbo");
            List<ChatMessage> listMessages = new ArrayList<>();
            listMessages.add(new ChatMessage("system", "You are a helpful assistant."));
            listMessages.add(new ChatMessage("user", request));
            chatRequest.setMessages(listMessages);
            chatRequest.setN(1);
            chatRequest.setTemperature(1);
            chatRequest.setMaxTokens(400);
            chatRequest.setStream(false);
            chatRequest.setPresencePenalty(1);

            System.out.println(chatRequest.getMessages());

            ChatResponse response = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .headers(h -> h.setBearerAuth("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"))
                    .bodyValue(chatRequest)
                    .retrieve()
                    .bodyToMono(ChatResponse.class)
                    .block();

            List<Choice> list = response.getChoices();
            System.out.println(list);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            // Log the exception (you might want to handle it differently in a production scenario)
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }



}

