package com.ciper.lakerhome.entity;

public class Video {
    private Integer id;

    private String title;

    private String place;

    private Integer starsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getStarsId() {
        return starsId;
    }

    public void setStarsId(Integer starsId) {
        this.starsId = starsId;
    }
}