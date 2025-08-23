package com.my.modong_prac.dto.storeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class StoreRequestDto {
    
    @Schema(description = "매장 ID", example = "store001")
    private String storeId;
    
    @Schema(description = "매장명", example = "토리쿠")
    private String storeName;
    
    @Schema(description = "매장 상세주소", example = "서울 노원구 공릉동 644-49")
    private String detail;
    
    @Schema(description = "매장 전화번호", example = "02-1234-5678")
    private String phone;
    
    @Schema(description = "운영시간", example = "09:00 - 22:00")
    private String operatingHours;
    
    @Schema(description = "카테고리", example = "한식")
    private String category;
    
    @Schema(description = "대표 메뉴", example = "김치찌개, 된장찌개")
    private String mainMenu;
    
    @Schema(description = "매장 설명", example = "정성스럽게 만드는 전통 한식 맛집입니다.")
    private String description;
    
    @Schema(description = "매장 분위기", example = "아늑한\n조용한\n가족적인")a 서
    private String storeMood;
}