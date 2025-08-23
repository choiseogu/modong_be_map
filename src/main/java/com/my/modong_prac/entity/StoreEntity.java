package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jn_store", 
       indexes = {},
       uniqueConstraints = {},
       catalog = "",
       schema = "")
@Data
public class StoreEntity {
    @Id
    @Column(name = "store_id")
    private String storeId;
    
    @Column(name = "store_name", nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String storeName;

    @Column(name = "detail", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String detail;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "operating_hours")
    private String operatingHours;
    
    @Column(name = "category", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String category;
    
    @Column(name = "main_menu", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String mainMenu;
    
    @Column(name = "description", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String description;
    
    @Column(name = "store_mood", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String storeMood;
}
