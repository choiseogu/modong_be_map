package com.my.modong_prac.dto.favoriteStoreDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FsRequestDto {

    @Schema(description = "user id", example = "johndoe123")
    private String userId;

    @Schema(description = "store name", example = "토리쿠")
    private String storeName;

    @Schema(description = "store detail", example = "서울 노원구 공릉동 644-49")
    private String storeDetail;
}
