package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "js_info")
@IdClass(JjimStoreId.class)
@Data
public class JjimStoreEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jt_id")
    private JjimTitleEntity jtId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private FavoriteStoreEntity storeId;
}
