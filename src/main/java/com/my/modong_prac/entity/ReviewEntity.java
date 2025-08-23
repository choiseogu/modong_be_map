package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "store_review")
@IdClass(ReviewId.class)
@Data
public class ReviewEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity storeId;

    @Column(name = "store_review", columnDefinition = "VARCHAR(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String content;
}
