package com.my.modong_prac.dto.JjimStoreDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JsRequestDto {
    @Schema(description = "jjimtitle id", example = "1")
    private Integer jtId;

    @Schema(description = "store id", example = "cafe1")
    private String storeId;
}
