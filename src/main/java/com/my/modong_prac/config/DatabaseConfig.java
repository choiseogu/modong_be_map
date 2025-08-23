package com.my.modong_prac.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mariadb://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul&connectionCollation=utf8mb4_unicode_ci&characterSetResults=utf8mb4&createDatabaseIfNotExist=true")
                .username("root")
                .password("1234")
                .driverClassName("org.mariadb.jdbc.Driver")
                .build();
    }
}