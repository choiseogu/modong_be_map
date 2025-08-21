package com.my.modong_prac.service.jtService;

import com.my.modong_prac.dto.JjimTitleDto.JtRequestDto;
import com.my.modong_prac.dto.JjimTitleDto.JtResponseDto;
import com.my.modong_prac.entity.JjimTitleEntity;

import java.util.List;
import java.util.Optional;

public interface JtService {
    JtResponseDto createJt(JtRequestDto jtRequestDto);
    JtResponseDto updateJt(Integer jtId, JtRequestDto jtRequestDto);
    List<JjimTitleEntity> getJtByUserId(Integer jtId);
    void deleteJt(Integer jtId);
}
