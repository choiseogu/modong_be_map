package com.my.modong_prac.controller;


import com.my.modong_prac.dto.jjimStoreDto.JsRequestDto;
import com.my.modong_prac.dto.jjimStoreDto.JsResponseDto;
import com.my.modong_prac.service.jsService.jsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "js_info", description = "찜한 매장 api")
@RestController
@RequestMapping("/api/v4")
public class JsController {

    private final jsService jsService;

    public JsController(jsService jsService) {
        this.jsService = jsService;
    }

    @PostMapping("/createJs")
    @Operation(summary = "찜한 매장 등록", description = "찜 제목, 매장id로 등록")
    public ResponseEntity<JsResponseDto> pushJs(@RequestBody JsRequestDto jsRequestDto) {
        JsResponseDto jsResponseDto = jsService.createJs(jsRequestDto);

        return ResponseEntity.ok(jsResponseDto);
    }

    @GetMapping("/getJs/{jtId}")
    @Operation(summary = "찜 제목에 해당하는 매장 정보 read", description = "json 형식 데이터 return")
    public ResponseEntity<List<JsResponseDto>> getJs(@PathVariable Integer jtId) {
        List<JsResponseDto> jsEntities = jsService.getJs(jtId)
                .stream()
                .map(JsResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(jsEntities);
    }

    @DeleteMapping("/deleteJs/{jtId}/{storeId}")
    @Operation(summary = "찜 제목에 해당하는 찜 매장 삭제", description = "찜 제목, 찜 매장 id 받아 해당 js 삭제")
    public ResponseEntity<Void> deleteJs(@PathVariable Integer jtId, @PathVariable String storeId) {
        jsService.deleteJs(jtId, storeId);
        return ResponseEntity.ok().build();
    }
}
