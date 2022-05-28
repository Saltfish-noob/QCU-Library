package edu.qcu.domain;

import java.io.Serializable;

public class RecommendIndex implements Serializable {
    private String userId1;
    private String UserId2;
    private Double index;

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return UserId2;
    }

    public void setUserId2(String userId2) {
        UserId2 = userId2;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "RecommendIndex{" +
                "userId1='" + userId1 + '\'' +
                ", UserId2='" + UserId2 + '\'' +
                ", index=" + index +
                '}';
    }
}
