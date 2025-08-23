package com.my.modong_prac.controller;

import com.my.modong_prac.dto.jjimTitleDto.JtRequestDto;
import com.my.modong_prac.dto.jjimTitleDto.JtResponseDto;
import com.my.modong_prac.service.jtService.JtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "jt_info", description = "찜 제목 api")
@RestController
@RequestMapping("/api/v3")
public class JtController {
    private final JtService jtService;

    public JtController(JtService jtService) {
        this.jtService = jtService;
    }

    @PostMapping("/createJt")
    @Operation(summary = "찜제목 생성", description = "json형식의 requestBody로 데이터 받아 생성")
    public ResponseEntity<JtResponseDto> createJt(@RequestBody JtRequestDto jtRequestDto) {
        JtResponseDto jtResponseDto = jtService.createJt(jtRequestDto);

        return ResponseEntity.ok(jtResponseDto);
    }

    @PutMapping("/updateJt/{jtId}")
    @Operation(summary = "사용자의 찜 제목 수정", description = "auto incremetn 설정인 jtId를 통해서 접근해서 수정하는 로직이므로 주의")
    public ResponseEntity<JtResponseDto> updateJt(@PathVariable Integer jtId, @RequestBody JtRequestDto jtRequestDto) {
        JtResponseDto jtResponseDto = jtService.updateJt(jtId, jtRequestDto);

        return  ResponseEntity.ok(jtResponseDto);
    }

    @GetMapping("/getAllJt")
    @Operation(summary = "전체 찜 제목 조회", description = "모든 사용자의 찜 제목을 조회합니다")
    public ResponseEntity<List<JtResponseDto>> getAllJt() {
        List<JtResponseDto> allJtEntities = jtService.getAllJt()
                .stream().map(JtResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(allJtEntities);
    }

    @GetMapping("/findJt/{jtId}")
    @Operation(summary = "jtId 조회로 사용자가 정의한 찜 제목 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<JtResponseDto>> findJt(@PathVariable Integer jtId) {
        List<JtResponseDto> jtEntities = jtService.getJtByUserId(jtId)
                .stream().map(JtResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jtEntities);
    }

    @DeleteMapping("/deleteJt/{jtId}")
    @Operation(summary = "찜 제목 삭제", description = "jtId로 지정하여 삭제")
    public ResponseEntity<Void> deleteJt(@PathVariable Integer jtId) {
        jtService.deleteJt(jtId);
        return ResponseEntity.ok().build();
    }
}
