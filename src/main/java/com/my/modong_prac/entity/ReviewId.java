package com.my.modong_prac.entity;

import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable {
    private String userId;
    private String storeId;

    public ReviewId() {}
    public ReviewId(String userId, String storeId) {
        this.userId = userId;
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewId)) return false;
        ReviewId that = (ReviewId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, storeId);
    }
}
