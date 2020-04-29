package com.ciper.lakerhome.entity;

public class UserStarsConnect {
    private Integer id;

    private String userId;

    private Integer starsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStarsId() {
        return starsId;
    }

    public void setStarsId(Integer starsId) {
        this.starsId = starsId;
    }
}