package com.my.modong_prac.repository;

import com.my.modong_prac.entity.FavoriteStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteStoreRepository extends JpaRepository<FavoriteStoreEntity,String> {
    Optional<FavoriteStoreEntity> findByStoreId(Long storeId);

    // 1. 이름으로 검색
    List<FavoriteStoreEntity> findByStoreName(String storeName);

    // 2. 이름에 특정 단어가 포함된 가게 검색 (부분 일치)
    List<FavoriteStoreEntity> findByStoreNameContaining(String storeName);

    // 3. 이름과 상세 정보로 검색
    List<FavoriteStoreEntity> findByStoreNameContainingOrDetailContaining(String storeName, String detail);
}
