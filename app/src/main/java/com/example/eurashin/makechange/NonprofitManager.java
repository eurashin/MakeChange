package com.example.eurashin.makechange;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Eura  Shin on 7/19/2017.
 */

public class NonprofitManager {
    private Nonprofit[] nonprofits;
    protected int size;
    private final int MAX = 5;
    protected double leftover;

    public NonprofitManager() {
        nonprofits = new Nonprofit[MAX];
        size = 0;
        leftover = 100;
    }

    public NonprofitManager(ArrayList<Nonprofit> nonprofits) {
        this.nonprofits = new Nonprofit[MAX];
        leftover = 100;
        for(int i=0; i<nonprofits.size(); i++) {
            this.nonprofits[i] = nonprofits.get(i);
            leftover -= nonprofits.get(i).getPercentDonated();
        }
        size = nonprofits.size();
    }

    public void add(Nonprofit nonprofit) {
        if(size < MAX) {
            size++;
            nonprofits[size] = nonprofit;
            //when a new nonprofit is added, all values are reset
            double equalAmount = Math.round(100/size);
            for(int i=0; i<size; i++) {
                nonprofits[i].setPercentDonated(equalAmount);
            }
        }
    }

    public void delete(int index) {
        //shift everything back one
        for(int i=index+1; i<size; i++) {
            nonprofits[i-1] = nonprofits[i];
        }
        //delete last
        size--;
    }

    //replaces nonprofit with another nonprofit, keeping same percent
    public void insert(Nonprofit nonprofit, int index) {
        boolean shouldAdd = true;
        int i = 0;
        while(shouldAdd && i < size) {
            shouldAdd = !nonprofits[index].equals(nonprofit);
            i++;
        }

        if(shouldAdd) {
            double percentDonatedPrevious = nonprofits[index].getPercentDonated();
            nonprofits[index] = nonprofit;
            nonprofits[index].setPercentDonated(percentDonatedPrevious); //maintains the double
        }
    }

    public void changePercent(int index, double percent) {
        nonprofits[index].setPercentDonated(percent);

        //the lower indeces shrink
        leftover = 100;
        for(int i=0; i<=index; i++) {
            leftover -= nonprofits[i].getPercentDonated();
        }

        int numberNonprofitsLeft = size - index - 1;
        double lowerSplitPercentage = leftover / numberNonprofitsLeft;
        for(int i = index + 1; i < size; i++) { //set rest of lower values, don't touch upper
            nonprofits[i].setPercentDonated(lowerSplitPercentage);
        }

    }

    public int[] getNonprofitPercentages() {
        int [] percents = new int[size];
        for(int i=0; i<size; i++) {
            percents[i] = (int)Math.round(nonprofits[i].getPercentDonated());
        }
        return percents;
    }

    public int[] getPastPercentages() {
        int [] percents = new int[size];
        for(int i=0; i<size; i++) {
            percents[i] = (int)Math.round(nonprofits[i].getOldPercent());
        }
        return percents;
    }
}
