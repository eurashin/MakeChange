package com.example.eurashin.makechange;

/**
 * Created by Eura  Shin on 5/12/2017.
 */

public class MenuItem {
    private String name;
    private String imageSrc;

    public MenuItem(String name) {
        this.name = name;
        //this.imageSrc = imageSrc;
    }

    //getters
    public String getName() {return name;};
    public String getImageSrc() {return imageSrc;}

    //setters
    public void setName(String name) {this.name = name;}
    public void setImageSrc(String imageSrc) {this.imageSrc = imageSrc;}
}