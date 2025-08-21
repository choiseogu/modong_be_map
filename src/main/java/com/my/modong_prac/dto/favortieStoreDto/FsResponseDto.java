package com.my.modong_prac.dto.favortieStoreDto;

import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.UserEntity;
import lombok.Data;

@Data
public class FsResponseDto {
    private String storeName;
    private String userId;
    private String detail;
    private double posX;
    private double posY;



    public FsResponseDto(FavoriteStoreEntity favoriteStoreEntity) {
        this.storeName = favoriteStoreEntity.getStoreName();
        this.userId = favoriteStoreEntity.getUserId().getId();
        this.detail = favoriteStoreEntity.getDetail();
        this.posX = favoriteStoreEntity.getPosX();
        this.posY = favoriteStoreEntity.getPosY();
    }
}
