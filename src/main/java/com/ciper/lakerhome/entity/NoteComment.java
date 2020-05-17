package com.ciper.lakerhome.entity;

import java.sql.Timestamp;

public class NoteComment {
    private Integer id;

    private String content;

    private Timestamp time;

    private String user_id;

    private String reply_user_id;

    private Integer pid;

    private Integer note_id;

    public NoteComment(Integer id, String content, Timestamp time, String user_id, String reply_user_id, Integer pid, Integer note_id){
        this.id = id;
        this.content = content;
        this.time = time;
        this.user_id = user_id;
        this.reply_user_id = reply_user_id;
        this.pid = pid;
        this.note_id = note_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReply_user_id() {
        return reply_user_id;
    }

    public void setReply_user_id(String reply_user_id) {
        this.reply_user_id = reply_user_id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNote_id() {
        return note_id;
    }

    public void setNote_id(Integer note_id) {
        this.note_id = note_id;
    }
}
