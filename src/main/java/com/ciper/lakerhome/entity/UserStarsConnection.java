package com.ciper.lakerhome.entity;

public class UserStarsConnection {
    private Integer id;

    private Integer starsId;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStarsId() {
        return starsId;
    }

    public void setStarsId(Integer starsId) {
        this.starsId = starsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}