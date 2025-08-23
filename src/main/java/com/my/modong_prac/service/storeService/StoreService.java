package com.my.modong_prac.service.storeService;

import com.my.modong_prac.dto.storeDto.StoreRequestDto;
import com.my.modong_prac.entity.StoreEntity;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    StoreEntity createStore(StoreRequestDto storeRequestDto);
    List<StoreEntity> getAllStores();
    Optional<StoreEntity> getStoreById(String storeId);
    List<StoreEntity> getStoresByCategory(String category);
    List<StoreEntity> searchStoresByName(String name);
    StoreEntity updateStore(String storeId, StoreRequestDto storeRequestDto);
    void deleteStore(String storeId);
}