package com.my.modong_prac.service.fsService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.modong_prac.dto.favortieStoreDto.FsRequestDto;
import com.my.modong_prac.entity.FavoriteStoreEntity;
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
    public List<FavoriteStoreEntity> getFavoriteStores() {
        List<FavoriteStoreEntity> fs = fsRepository.findAll();

        if (fs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 fs 없음");
        }

        return fs;
    }

    @Override
    public List<FavoriteStoreEntity> getFavoriteStoresByUserId(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        FavoriteStoreEntity fs = fsRepository.findByUserId(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 userId에 등록된 가게 없음"));
        return Collections.singletonList(fs);
    }

    @Override
    public Mono<FavoriteStoreEntity> createFs(FsRequestDto fsRequestDto) {

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
                e.printStackTrace();
            }
            return Mono.just(fsRepository.save(fsEntity));
        });
    }

    private FavoriteStoreEntity toEntity(FsRequestDto fsRequestDto) {
        FavoriteStoreEntity entity = new FavoriteStoreEntity();
        entity.setStoreName(fsRequestDto.getStoreName());
        entity.setDetail(fsRequestDto.getStoreDetail());
        return entity;
    }
}
