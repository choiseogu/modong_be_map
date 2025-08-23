package com.my.modong_prac.service.jtService;

import com.my.modong_prac.dto.jjimTitleDto.JtRequestDto;
import com.my.modong_prac.dto.jjimTitleDto.JtResponseDto;
import com.my.modong_prac.entity.JjimTitleEntity;

import java.util.List;
import java.util.Optional;

public interface JtService {
    JtResponseDto createJt(JtRequestDto jtRequestDto);
    JtResponseDto updateJt(Integer jtId, JtRequestDto jtRequestDto);
    List<JjimTitleEntity> getJtByUserId(Integer jtId);
    List<JjimTitleEntity> getAllJt(); // 전체 JT 조회 메서드 추가
    void deleteJt(Integer jtId);
}
