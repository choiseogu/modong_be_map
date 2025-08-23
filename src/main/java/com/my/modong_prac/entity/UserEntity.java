package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
@Data
public class UserEntity {
    @Column(name = "id")
    @Id
    private String id;

    @Column(name = "address", columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String address;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_favorite_mood", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "user_mood", columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private List<String> userMood = new ArrayList<>();

    @Column(name = "user_stamp", nullable = false)
    private Integer userStamp = 0;


    public UserEntity() {}

    public UserEntity(String id, String address, List<String> userMood) {
        this.id = id;
        this.address = address;
        this.userMood = userMood;
    }
}
