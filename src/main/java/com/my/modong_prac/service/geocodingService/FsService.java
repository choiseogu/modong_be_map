package com.my.modong_prac.service.geocodingService;

import com.my.modong_prac.dto.favortieStoreDto.FsRequestDto;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import com.my.modong_prac.entity.UserEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FsService {
    List<FavoriteStoreEntity> getFavoriteStores();
    List<FavoriteStoreEntity> getFavoriteStoresByUserId(String userId);
    Mono<FavoriteStoreEntity> createFs(String userId, FsRequestDto fsRequestDto);
    //Mono<FavoriteStoreEntity> updateFs(String storeId, FsRequestDto fsRequestDto);
    Void deleteFavoriteStoresByStoreId(String storeId);
}
