package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_store")
@Data
public class FavoriteStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "detail")
    private String detail;

    @Column(name = "posX")
    private double posX;

    @Column(name = "posY")
    private double posY;
}
