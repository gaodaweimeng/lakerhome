package com.ciper.lakerhome.entity;

public class News {
    private Integer id;

    private String title;

    private String content;

    private Integer starsId;

    public News(Integer id, String title, String content,Integer starsId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.starsId = starsId;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStarsId() {
        return starsId;
    }

    public void setStarsId(Integer starsId) {
        this.starsId = starsId;
    }
}