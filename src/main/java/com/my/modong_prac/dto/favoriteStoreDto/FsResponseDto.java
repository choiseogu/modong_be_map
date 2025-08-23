package com.my.modong_prac.dto.favoriteStoreDto;

import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.UserEntity;
import lombok.Data;

@Data
public class FsResponseDto {
    private String storeName;  // 복합키 1
    private String detail;     // 복합키 2
    private String userId;

    public FsResponseDto(FavoriteStoreEntity favoriteStoreEntity) {
        this.storeName = favoriteStoreEntity.getStoreName();
        this.userId = favoriteStoreEntity.getUserId().getId();
        this.detail = favoriteStoreEntity.getDetail();
    }
}
