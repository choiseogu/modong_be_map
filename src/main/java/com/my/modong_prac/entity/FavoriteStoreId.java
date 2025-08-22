package com.my.modong_prac.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteStoreId implements Serializable {
    private String storeName;
    private String detail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteStoreId that = (FavoriteStoreId) o;
        return Objects.equals(storeName, that.storeName) && 
               Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName, detail);
    }
}