package com.example.minsookang.soms;

public class ChatVO {
    private String id;
    private String content;
    private String time;
    private int check;

    public ChatVO(){}

    public ChatVO(String id, String content, String time, int check) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.check = check;

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

    public int getCheck() {return check;}
}