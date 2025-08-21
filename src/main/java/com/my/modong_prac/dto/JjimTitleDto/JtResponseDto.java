package com.my.modong_prac.dto.JjimTitleDto;

import com.my.modong_prac.entity.JjimTitleEntity;
import lombok.Data;

@Data
public class JtResponseDto {
    private Integer jjimTitleId;
    private String userId;
    private String jjimTitle;

    public JtResponseDto(JjimTitleEntity jjimTitleEntity) {
        this.jjimTitleId = jjimTitleEntity.getJtId();
        this.userId = jjimTitleEntity.getUserId().getId();
        this.jjimTitle = jjimTitleEntity.getJjimTitle();
    }
}
