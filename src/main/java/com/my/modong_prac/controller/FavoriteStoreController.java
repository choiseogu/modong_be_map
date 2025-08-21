package com.my.modong_prac.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.repository.FavoriteStoreRepository;
import com.my.modong_prac.service.geocodingService.FsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Tag(name = "store_info", description = "정릉 매장 api")
@RestController
@RequestMapping("/api/v5")
public class FavoriteStoreController {
    private final FavoriteStoreRepository favoriteStoreRepository;
    private final FsServiceImpl fsServiceImpl;

    // 생성자 수정: GeocodingService 주입
    public FavoriteStoreController(FavoriteStoreRepository favoriteStoreRepository, FsServiceImpl fsServiceImpl) {
        this.favoriteStoreRepository = favoriteStoreRepository;
        this.fsServiceImpl = fsServiceImpl;
    }

    // 기존 모든 가게 조회 API
    @GetMapping
    public List<FavoriteStoreEntity> getAllStores() {
        return favoriteStoreRepository.findAll();
    }

    // 검색 기능 추가
    @GetMapping("/search")
    public List<FavoriteStoreEntity> searchStores(@RequestParam String name) {
        return favoriteStoreRepository.findByStoreNameContaining(name);
    }

    // 삭제 기능 추가
    @DeleteMapping("/delete/{storeId}")
    public void deleteStore(@PathVariable String storeId) {
        favoriteStoreRepository.deleteById(storeId);
    }

    // 지오코딩 기능이 추가된 새로운 가게 생성 API
    @PostMapping
    public Mono<FavoriteStoreEntity> createStore(@RequestBody FavoriteStoreEntity store) {
        Mono<String> geocodingResponse;

        // 1. detail 필드가 있으면 주소 기반 검색
        if (store.getDetail() != null && !store.getDetail().trim().isEmpty()) {
            geocodingResponse = fsServiceImpl.getCoordinatesFromAddress(store.getDetail());
        }
        // 2. detail 필드가 없으면 name 기반 키워드 검색
        else if (store.getStoreName() != null && !store.getStoreName().trim().isEmpty()) {
            geocodingResponse = fsServiceImpl.getCoordinatesFromKeyword(store.getStoreName());
        }
        // 3. name과 detail 모두 없으면 지오코딩 없이 저장
        else {
            return Mono.just(favoriteStoreRepository.save(store));
        }

        return geocodingResponse.flatMap(response -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);
                JsonNode documents = root.path("documents");

                if (documents.size() > 0) {
                    JsonNode firstDocument = documents.get(0);
                    double longitude = firstDocument.path("x").asDouble();
                    double latitude = firstDocument.path("y").asDouble();
                    String address = firstDocument.path("address_name").asText();

                    store.setPosX(longitude);
                    store.setPosY(latitude);
                    store.setDetail(address);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Mono.just(favoriteStoreRepository.save(store));
        });
    }

    // 수정 기능 추가
    @PutMapping("/{storeId}")
    public Mono<FavoriteStoreEntity> updateStore(@PathVariable String storeId, @RequestBody FavoriteStoreEntity updatedStore) {
        return Mono.just(favoriteStoreRepository.findById(storeId))
                .flatMap(optionalStore -> {
                    if (optionalStore.isPresent()) {
                        FavoriteStoreEntity store = optionalStore.get();

                        // 주소가 변경되었는지 확인
                        if (!store.getDetail().equals(updatedStore.getDetail())) {
                            // 주소가 변경되면 지오코딩을 수행하고 위도, 경도 업데이트
                            store.setStoreName(updatedStore.getStoreName());
                            store.setDetail(updatedStore.getDetail());
                            // 지오코딩 서비스 호출
                            return fsServiceImpl.getCoordinatesFromAddress(updatedStore.getDetail())
                                    .flatMap(response -> {
                                        try {
                                            ObjectMapper mapper = new ObjectMapper();
                                            JsonNode root = mapper.readTree(response);
                                            JsonNode documents = root.path("documents");

                                            if (documents.size() > 0) {
                                                JsonNode firstDocument = documents.get(0);
                                                double longitude = firstDocument.path("x").asDouble();
                                                double latitude = firstDocument.path("y").asDouble();

                                                store.setPosX(longitude);
                                                store.setPosY(latitude);
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        return Mono.just(favoriteStoreRepository.save(store));
                                    });
                        } else {
                            // 주소가 변경되지 않았으므로 지오코딩 없이 이름만 업데이트
                            store.setStoreName(updatedStore.getStoreName());
                            // 다른 필드도 여기서 업데이트
                            return Mono.just(favoriteStoreRepository.save(store));
                        }
                    }
                    return Mono.empty();
                });
    }
}
