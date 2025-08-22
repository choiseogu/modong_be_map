package com.my.modong_prac.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeocodingService {
    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakaoApiKey)
                .build();
    }

    public Mono<String> getCoordinatesFromAddress(String roadFullAddr) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/address.json")
                        .queryParam("query", roadFullAddr)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getCoordinatesFromKeyword(String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/local/search/keyword.json")
                        .queryParam("query", keyword)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
