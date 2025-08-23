package com.my.modong_prac.dto.storeDto;

import com.my.modong_prac.entity.StoreEntity;
import lombok.Data;

@Data
public class StoreResponseDto {
    private String storeId;
    private String storeName;
    private String detail;
    private String phone;
    private String operatingHours;
    private String category;
    private String mainMenu;
    private String description;
    private String storeMood;
    
    public StoreResponseDto(StoreEntity storeEntity) {
        this.storeId = storeEntity.getStoreId();
        this.storeName = storeEntity.getStoreName();
        this.detail = storeEntity.getDetail();
        this.phone = storeEntity.getPhone();
        this.operatingHours = storeEntity.getOperatingHours();
        this.category = storeEntity.getCategory();
        this.mainMenu = storeEntity.getMainMenu();
        this.description = storeEntity.getDescription();
        this.storeMood = storeEntity.getStoreMood();
    }
}