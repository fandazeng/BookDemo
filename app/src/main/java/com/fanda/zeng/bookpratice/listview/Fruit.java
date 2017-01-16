package com.fanda.zeng.bookpratice.listview;

/**
 * Created by zfd on 2017/1/16 0016.
 *  水果实体类
 */

public class Fruit {
    private String name ;
    private int imageId ;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
