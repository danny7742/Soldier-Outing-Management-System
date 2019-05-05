package com.example.minsookang.soms;

public class ChatVO {
    private String id;
    private String content;
    private String time;

    public ChatVO(){}

    public ChatVO(String id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}