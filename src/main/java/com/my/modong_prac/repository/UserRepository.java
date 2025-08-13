package com.my.modong_prac.repository;

import com.my.modong_prac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    boolean existsById(String id);

    Optional<UserEntity> findById(String id);
}
