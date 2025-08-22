package com.my.modong_prac.service.fsService;

import com.my.modong_prac.dto.favoriteStoreDto.FsRequestDto;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FsService {
    List<FavoriteStoreEntity> getAllFs();
    List<FavoriteStoreEntity> getUserFs(String userId);
    Mono<FavoriteStoreEntity> createFs(FsRequestDto fsRequestDto);
    //Mono<FavoriteStoreEntity> updateFs(String storeId, FsRequestDto fsRequestDto);
    //Void deleteFavoriteStoresByStoreId(String storeId);
}
