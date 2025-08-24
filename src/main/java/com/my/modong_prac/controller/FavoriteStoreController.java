package com.my.modong_prac.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.modong_prac.dto.favoriteStoreDto.FsRequestDto;
import com.my.modong_prac.dto.favoriteStoreDto.FsResponseDto;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.FavoriteStoreId;
import com.my.modong_prac.repository.FsRepository;
import com.my.modong_prac.service.fsService.FsService;
import com.my.modong_prac.service.fsService.FsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "favoriteStore_info", description = "유저 최애 매장 api")
@RestController
@RequestMapping("/api/v5")
@CrossOrigin(origins = "*")
public class FavoriteStoreController {
    private final FsRepository fsRepository;
    private final FsService fsService;

    // 생성자 수정: GeocodingService 주입
    public FavoriteStoreController(FsRepository fsRepository, FsService fsService) {
        this.fsRepository = fsRepository;
        this.fsService = fsService;
    }

    // 기존 모든 가게 조회 API
    @GetMapping("/getAllFs")
    public ResponseEntity<List<FsResponseDto>> getAllFs() {
        List<FsResponseDto> allFs = fsRepository.findAll()
                .stream()
                .map(FsResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allFs);
    }

    // 검색 기능 추가
    @GetMapping("/getUserFs")
    public ResponseEntity<List<FsResponseDto>> getFs(@RequestParam String userId) {
        List<FsResponseDto> userFs = fsService.getUserFs(userId)
                .stream()
                .map(FsResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userFs);
    }

    // 특정 가게 조회 API 추가 - 복합키 사용
    @GetMapping("/store/{storeName}/{detail}")
    public ResponseEntity<FsResponseDto> getStore(@PathVariable String storeName, @PathVariable String detail) {
        FavoriteStoreId favoriteStoreId = new FavoriteStoreId(storeName, detail);
        return fsRepository.findById(favoriteStoreId)
                .map(entity -> ResponseEntity.ok(new FsResponseDto(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    // 가게 이름으로만 검색하는 API (여러 결과 가능)
    @GetMapping("/store/{storeName}")
    public ResponseEntity<List<FsResponseDto>> getStoresByName(@PathVariable String storeName) {
        List<FsResponseDto> stores = fsRepository.findByStoreName(storeName)
                .stream()
                .map(FsResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stores);
    }

    // 삭제 기능 추가 - 복합키 사용
    @DeleteMapping("/delete/{storeName}/{detail}")
    public ResponseEntity<Void> deleteStore(@PathVariable String storeName, @PathVariable String detail) {
        FavoriteStoreId favoriteStoreId = new FavoriteStoreId(storeName, detail);
        if (fsRepository.existsById(favoriteStoreId)) {
            fsRepository.deleteById(favoriteStoreId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 지오코딩 기능이 추가된 새로운 가게 생성 API
    @PostMapping
    public Mono<ResponseEntity<FsResponseDto>> createStore(@RequestBody FsRequestDto fsRequestDto) {
        return fsService.createFs(fsRequestDto)
                .map(entity -> ResponseEntity.ok(new FsResponseDto(entity)))
                .onErrorReturn(ResponseEntity.badRequest().build());
    }

}
