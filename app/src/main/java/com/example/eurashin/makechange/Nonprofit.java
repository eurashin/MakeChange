package com.example.eurashin.makechange;

import android.content.Context;
import android.widget.SeekBar;

/**
 * Created by Eura  Shin on 7/19/2017.
 */

public class Nonprofit {
    private String name;
    private double percentDonated;
    private double total;
    private double oldPercent;

    public Nonprofit(String name, double percentDonated) {
        this.name = name;
        this.percentDonated = percentDonated;
        this.oldPercent = 0;
    }

    public String getName(){return name;}
    public double getPercentDonated(){return percentDonated;}
    public double getOldPercent(){return oldPercent;}
    public double getTotal(){return total;}

    public void setName(String name){this.name = name;}
    public void setPercentDonated(double percentDonated){this.percentDonated = percentDonated;}
    public void setOldPercent(double oldPercent) {this.oldPercent = oldPercent;}
    public void setTotal(double total){this.total = total;}
    public boolean equals(Nonprofit nonprofit) {
        return(this.name.equalsIgnoreCase(nonprofit.getName()));
    }
}
