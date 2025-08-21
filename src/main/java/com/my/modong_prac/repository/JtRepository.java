package com.my.modong_prac.repository;

import com.my.modong_prac.entity.JjimTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JtRepository extends JpaRepository<JjimTitleEntity,String> {
    Optional<JjimTitleEntity> findByJtId(Integer jtId);
}
