package com.my.modong_prac.service.storeService;

import com.my.modong_prac.dto.storeDto.StoreRequestDto;
import com.my.modong_prac.entity.StoreEntity;
import com.my.modong_prac.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreEntity createStore(StoreRequestDto storeRequestDto) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setStoreId(storeRequestDto.getStoreId());
        storeEntity.setStoreName(storeRequestDto.getStoreName());
        storeEntity.setDetail(storeRequestDto.getDetail());
        storeEntity.setPhone(storeRequestDto.getPhone());
        storeEntity.setOperatingHours(storeRequestDto.getOperatingHours());
        storeEntity.setCategory(storeRequestDto.getCategory());
        storeEntity.setMainMenu(storeRequestDto.getMainMenu());
        storeEntity.setDescription(storeRequestDto.getDescription());
        storeEntity.setStoreMood(storeRequestDto.getStoreMood());
        
        return storeRepository.save(storeEntity);
    }

    @Override
    public List<StoreEntity> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<StoreEntity> getStoreById(String storeId) {
        return storeRepository.findById(storeId);
    }

    @Override
    public List<StoreEntity> getStoresByCategory(String category) {
        return storeRepository.findByCategory(category);
    }

    @Override
    public List<StoreEntity> searchStoresByName(String name) {
        return storeRepository.findByStoreNameContainingIgnoreCase(name);
    }

    @Override
    public StoreEntity updateStore(String storeId, StoreRequestDto storeRequestDto) {
        StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + storeId));
        
        storeEntity.setStoreName(storeRequestDto.getStoreName());
        storeEntity.setDetail(storeRequestDto.getDetail());
        storeEntity.setPhone(storeRequestDto.getPhone());
        storeEntity.setOperatingHours(storeRequestDto.getOperatingHours());
        storeEntity.setCategory(storeRequestDto.getCategory());
        storeEntity.setMainMenu(storeRequestDto.getMainMenu());
        storeEntity.setDescription(storeRequestDto.getDescription());
        storeEntity.setStoreMood(storeRequestDto.getStoreMood());
        
        return storeRepository.save(storeEntity);
    }

    @Override
    public void deleteStore(String storeId) {
        storeRepository.deleteById(storeId);
    }
}