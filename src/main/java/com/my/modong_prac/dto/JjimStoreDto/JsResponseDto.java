package com.my.modong_prac.dto.jjimStoreDto;

import com.my.modong_prac.entity.JjimStoreEntity;
import lombok.Data;

@Data
public class JsResponseDto {
    private Integer jtId;
    private String storeId;
    private String storeName;

    public JsResponseDto(JjimStoreEntity jjimStoreEntity) {
        this.jtId = jjimStoreEntity.getJtId().getJtId();
        this.storeId = jjimStoreEntity.getStoreId().getStoreId();
        this.storeName = jjimStoreEntity.getStoreId().getStoreName();
    }
}
