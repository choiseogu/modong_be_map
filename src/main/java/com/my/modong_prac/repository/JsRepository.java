package com.my.modong_prac.repository;

import com.my.modong_prac.entity.JjimStoreEntity;
import com.my.modong_prac.entity.JjimTitleEntity;
import com.my.modong_prac.entity.FavoriteStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JsRepository extends JpaRepository<JjimStoreEntity, String> {

    JjimStoreEntity findByJtIdAndStoreId(JjimTitleEntity jtId, FavoriteStoreEntity storeId);
    List<JjimStoreEntity> findByJtId(JjimTitleEntity jtId);
}
