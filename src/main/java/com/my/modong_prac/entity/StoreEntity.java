package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jn_store")
@Data
public class StoreEntity {
    @Id
    @Column(name = "store_id")
    private String storeId;
    
    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "detail", columnDefinition = "TEXT")
    private String detail;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "operating_hours")
    private String operatingHours;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "main_menu")
    private String mainMenu;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "store_mood")
    private String storeMood;
}
