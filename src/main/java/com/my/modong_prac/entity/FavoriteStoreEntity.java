package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_store")
@IdClass(FavoriteStoreId.class)
@Data
public class FavoriteStoreEntity {

    @Id
    @Column(name = "store_name")
    private String storeName;

    @Id
    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "posX")
    private double posX;

    @Column(name = "posY")
    private double posY;
}