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

    @Column(name = "address")
    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_favorite_place", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "user_place")
    private List<String> userPlace = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_favorite_mood", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "user_mood")
    private List<String> userMood = new ArrayList<>();

    public UserEntity() {}

    public UserEntity(String id, String address, List<String> userPlace, List<String> userMood) {
        this.id = id;
        this.address = address;
        this.userPlace = userPlace;
        this.userMood = userMood;
    }
}
