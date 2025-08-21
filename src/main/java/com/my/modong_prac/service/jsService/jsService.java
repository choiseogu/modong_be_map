package com.my.modong_prac.service.jsService;

import com.my.modong_prac.dto.JjimStoreDto.JsRequestDto;
import com.my.modong_prac.dto.JjimStoreDto.JsResponseDto;
import com.my.modong_prac.entity.JjimStoreEntity;

import java.util.List;
import java.util.Optional;

public interface jsService {
    JsResponseDto createJs(JsRequestDto jsRequestDto);
    List<JjimStoreEntity> getJs(Integer jtId);
    void deleteJs(Integer jtId, String storeId);
}
