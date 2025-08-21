package com.my.modong_prac.entity;

import java.io.Serializable;
import java.util.Objects;

public class JjimStoreId implements Serializable {
    private Integer jtId;
    private String storeId;

    public JjimStoreId() {}
    public JjimStoreId(Integer jtId, String storeId) {
        this.jtId = jtId;
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JjimStoreId)) return false;
        JjimStoreId that = (JjimStoreId) o;
        return Objects.equals(jtId, that.jtId) && Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jtId, storeId);
    }
}
