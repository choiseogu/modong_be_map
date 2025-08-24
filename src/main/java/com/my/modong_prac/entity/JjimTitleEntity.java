package com.my.modong_prac.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jt_info")
@Data
public class JjimTitleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jjimTitle_id")
    private Integer jtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    @Column(name = "jjim_title")
    private String jjimTitle;
}
