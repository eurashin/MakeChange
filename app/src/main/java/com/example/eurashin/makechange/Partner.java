package com.example.eurashin.makechange;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 5/3/2017.
 */

public class Partner {
    private String name;
    private String imageSrc;
    private String summary;
    private ArrayList<String> tags;
    private String address;

    public Partner(String name, String imageSrc, String summary, String address, ArrayList<String> tags) {
        this.name = name;
        this.imageSrc = imageSrc;
        this.summary = summary;
        this.address = address;
        this.tags = tags;
    }

    //getters
    public String getName() {return name;}
    public String getImageSrc() {return imageSrc;}
    public String getSummary() {return summary;}
    public String getAddress() {return address;}


    //setters
    public void setName(String name) {this.name = name;}

}

