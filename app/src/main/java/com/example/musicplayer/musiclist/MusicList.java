package com.example.musicplayer.musiclist;

public class MusicList {
    private String name;
    private String description;
    private int img;

    public MusicList(String name, String description, int img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public int getImg() {
        return img;
    }
}
