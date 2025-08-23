package com.my.modong_prac.repository;

import com.my.modong_prac.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, String>{
    List<StoreEntity> findByCategory(String category);
    List<StoreEntity> findByStoreNameContainingIgnoreCase(String storeName);
}
