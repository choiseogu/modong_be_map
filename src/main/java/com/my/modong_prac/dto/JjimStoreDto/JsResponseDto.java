package com.my.modong_prac.dto.JjimStoreDto;

import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.JjimStoreId;
import com.sun.source.util.JavacTask;
import lombok.Data;

@Data
public class JsResponseDto {
    private Integer jtId;
    private String storeId;

    public JsResponseDto(JjimStoreEntity jjimStoreEntity) {
        this.jtId = jjimStoreEntity.getJtId().getJtId();
        this.storeId = jjimStoreEntity.getStoreId().getStoreName();
    }
}
