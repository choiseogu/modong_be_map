package com.my.modong_prac.controller;

import com.my.modong_prac.dto.storeDto.StoreRequestDto;
import com.my.modong_prac.dto.storeDto.StoreResponseDto;
import com.my.modong_prac.entity.StoreEntity;
import com.my.modong_prac.service.storeService.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "store_info", description = "매장 정보 api")
@RestController
@RequestMapping("/api/v6")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/createStore")
    @Operation(summary = "매장 생성", description = "json형식의 requestBody를 통한 매장 생성")
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody @Valid StoreRequestDto storeRequestDto) {
        StoreEntity storeEntity = storeService.createStore(storeRequestDto);
        return ResponseEntity.ok(new StoreResponseDto(storeEntity));
    }

    @GetMapping("/getAllStores")
    @Operation(summary = "모든 매장 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<StoreResponseDto>> getAllStores() {
        List<StoreResponseDto> stores = storeService.getAllStores()
                .stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "특정 매장 조회", description = "매장 ID로 특정 매장 조회")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable String storeId) {
        return storeService.getStoreById(storeId)
                .map(StoreResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "카테고리별 매장 조회", description = "카테고리로 매장 검색")
    public ResponseEntity<List<StoreResponseDto>> getStoresByCategory(@PathVariable String category) {
        List<StoreResponseDto> stores = storeService.getStoresByCategory(category)
                .stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/search")
    @Operation(summary = "매장명으로 검색", description = "매장명으로 매장 검색")
    public ResponseEntity<List<StoreResponseDto>> searchStoresByName(@RequestParam String name) {
        List<StoreResponseDto> stores = storeService.searchStoresByName(name)
                .stream()
                .map(StoreResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stores);
    }

    @PutMapping("/{storeId}")
    @Operation(summary = "매장 정보 수정", description = "매장 ID로 매장 정보 수정")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable String storeId, @RequestBody @Valid StoreRequestDto storeRequestDto) {
        try {
            StoreEntity storeEntity = storeService.updateStore(storeId, storeRequestDto);
            return ResponseEntity.ok(new StoreResponseDto(storeEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{storeId}")
    @Operation(summary = "매장 삭제", description = "매장 ID로 매장 삭제")
    public ResponseEntity<Void> deleteStore(@PathVariable String storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }
}