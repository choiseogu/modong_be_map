package com.my.modong_prac.repository;

import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.FavoriteStoreId;
import com.my.modong_prac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FsRepository extends JpaRepository<FavoriteStoreEntity, FavoriteStoreId> {
    // 복합키로 변경: findById(FavoriteStoreId)를 사용

    // 1. 가게 이름으로 검색
    List<FavoriteStoreEntity> findByStoreName(String storeName);
    
    // 2. 이름에 특정 단어가 포함된 가게 검색 (부분 일치)
    List<FavoriteStoreEntity> findByStoreNameContaining(String storeName);

    // 3. 사용자별 가게 검색
    List<FavoriteStoreEntity> findByUserId(UserEntity userId);

    // 4. 이름과 상세 정보로 검색
    List<FavoriteStoreEntity> findByStoreNameContainingOrDetailContaining(String storeName, String detail);
    
    // 5. 가게 이름과 상세 정보로 정확히 검색
    Optional<FavoriteStoreEntity> findByStoreNameAndDetail(String storeName, String detail);
}
