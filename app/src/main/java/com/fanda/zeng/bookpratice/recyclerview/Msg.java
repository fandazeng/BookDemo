package com.fanda.zeng.bookpratice.recyclerview;

/**
 * Created by zfd on 2017/1/17 0017.
 * 消息实体类
 */

public class Msg {

    public static final int TYPE_SEND = 1;
    public static final int TYPE_RECEIVE = 0;

    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
