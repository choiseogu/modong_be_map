package com.my.modong_prac.service.fsService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.modong_prac.dto.favoriteStoreDto.FsRequestDto;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.FavoriteStoreId;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.repository.FsRepository;
import com.my.modong_prac.repository.UserRepository;
import com.my.modong_prac.service.GeocodingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class FsServiceImpl implements FsService {

    private final FsRepository fsRepository;
    private final UserRepository userRepository;
    private final GeocodingService geocodingService;

    public FsServiceImpl(FsRepository fsRepository, UserRepository userRepository, GeocodingService geocodingService) {
        this.fsRepository = fsRepository;
        this.userRepository = userRepository;
        this.geocodingService = geocodingService;
    }

    @Override
    public List<FavoriteStoreEntity> getAllFs() {
        List<FavoriteStoreEntity> fs = fsRepository.findAll();

        if (fs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 fs 없음");
        }

        return fs;
    }

    @Override
    public List<FavoriteStoreEntity> getUserFs(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return fsRepository.findByUserId(user);
    }

    @Override
    public Mono<FavoriteStoreEntity> createFs(FsRequestDto fsRequestDto) {
        // 복합키 중복 체크를 위해 임시 detail 설정
        String tempDetail = fsRequestDto.getStoreDetail() != null ? fsRequestDto.getStoreDetail() : "";
        
        // 동일한 storeName + detail 조합이 이미 존재하는지 체크
        if (fsRepository.findByStoreNameAndDetail(fsRequestDto.getStoreName(), tempDetail).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "동일한 가게명과 상세정보가 이미 존재합니다");
        }

        Mono<String> geocodingResponse;

        // 1. detail 필드가 있으면 주소 기반 검색
        if (fsRequestDto.getStoreDetail() != null && !fsRequestDto.getStoreDetail().trim().isEmpty()) {
            geocodingResponse = geocodingService.getCoordinatesFromAddress(fsRequestDto.getStoreDetail());
        }
        // 2. detail 필드가 없으면 name 기반 키워드 검색
        else if (fsRequestDto.getStoreName() != null && !fsRequestDto.getStoreName().trim().isEmpty()) {
            geocodingResponse = geocodingService.getCoordinatesFromKeyword(fsRequestDto.getStoreName());
        }
        // 3. name과 detail 모두 없으면 지오코딩 없이 저장
        else {
            FavoriteStoreEntity entity = toEntity(fsRequestDto);
            return Mono.just(fsRepository.save(entity));
        }

        FavoriteStoreEntity fsEntity = new FavoriteStoreEntity();
        UserEntity userId = userRepository.findById(fsRequestDto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        fsEntity.setUserId(userId);
        fsEntity.setStoreName(fsRequestDto.getStoreName());

        return geocodingResponse.flatMap(response -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response);
                JsonNode documents = root.path("documents");

                if (documents.size() > 0) {
                    JsonNode firstDocument = documents.get(0);
                    double longitude = firstDocument.path("x").asDouble();
                    double latitude = firstDocument.path("y").asDouble();
                    String address = firstDocument.path("address_name").asText();

                    fsEntity.setPosX(longitude);
                    fsEntity.setPosY(latitude);
                    fsEntity.setDetail(address);
                }
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "지오코딩 응답 파싱 실패", e);
            }
            return Mono.just(fsRepository.save(fsEntity));
        });
    }

    private FavoriteStoreEntity toEntity(FsRequestDto fsRequestDto) {
        FavoriteStoreEntity entity = new FavoriteStoreEntity();
        UserEntity user = userRepository.findById(fsRequestDto.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        entity.setUserId(user);
        entity.setStoreName(fsRequestDto.getStoreName());
        entity.setDetail(fsRequestDto.getStoreDetail());
        return entity;
    }
}
