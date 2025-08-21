package com.my.modong_prac.repository;

import com.my.modong_prac.entity.ReviewEntity;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity,String> {
    boolean existsByUserIdAndStoreId(UserEntity userId, FavoriteStoreEntity storeId);

    Optional<ReviewEntity> findByUserIdAndStoreId(UserEntity userId, FavoriteStoreEntity storeId);
    List<ReviewEntity> findByUserId(UserEntity userId);
    List<ReviewEntity> findByStoreId(FavoriteStoreEntity storeId);
}
