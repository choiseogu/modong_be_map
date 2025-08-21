package com.my.modong_prac.dto.favortieStoreDto;

import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.UserEntity;
import lombok.Data;

@Data
public class FsResponseDto {
    private Long storeId;
    private String userId;
    private String detail;
    private double posX;
    private double posY;
    private String storeName;


    public FsResponseDto(FavoriteStoreEntity favoriteStoreEntity) {
        this.storeId = favoriteStoreEntity.getStoreId();
        this.userId = favoriteStoreEntity.getUserId().getId();
        this.detail = favoriteStoreEntity.getDetail();
        this.posX = favoriteStoreEntity.getPosX();
        this.posY = favoriteStoreEntity.getPosY();
        this.storeName = favoriteStoreEntity.getStoreName();
    }
}
