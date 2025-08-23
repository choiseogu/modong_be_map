package com.my.modong_prac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // 데이터베이스 존재 확인 및 생성
            createDatabaseIfNotExists();
            
            // UTF-8 설정 적용
            setUtf8Settings();
            
            System.out.println("✅ Database initialized successfully with UTF-8 settings");
        } catch (Exception e) {
            System.err.println("❌ Database initialization failed: " + e.getMessage());
        }
    }

    private void createDatabaseIfNotExists() {
        try {
            jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS mydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            jdbcTemplate.execute("USE mydb");
        } catch (Exception e) {
            System.out.println("Database already exists or creation failed: " + e.getMessage());
        }
    }

    private void setUtf8Settings() {
        try {
            jdbcTemplate.execute("SET NAMES utf8mb4");
            jdbcTemplate.execute("SET CHARACTER SET utf8mb4");
            jdbcTemplate.execute("SET character_set_connection=utf8mb4");
        } catch (Exception e) {
            System.out.println("UTF-8 settings failed: " + e.getMessage());
        }
    }
}