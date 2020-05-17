package com.ciper.lakerhome.entity;

public class Note {
    private Integer id;

    private String title;

    private String content;

    private String user_id;

    public Note(Integer id, String title, String content, String user_id){
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
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
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
