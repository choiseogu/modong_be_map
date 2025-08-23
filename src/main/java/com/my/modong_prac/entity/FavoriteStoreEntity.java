package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_store")
@IdClass(FavoriteStoreId.class)
@Data
public class FavoriteStoreEntity {

    @Id
    @Column(name = "store_name", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String storeName;

    @Id
    @Column(name = "detail", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String detail;

    @Column(name = "pos_x")
    private Double posX;

    @Column(name = "pos_y")
    private Double posY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;
}