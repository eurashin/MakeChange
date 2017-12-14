package com.example.eurashin.makechange;

/**
 * Created by eurashin on 10/11/17.
 */

public class Donation {
    private String nonprofit;
    private int day, month, year;
    private double amount;

    public Donation(String nonprofit, double amount) {
        this.day = 0;
        this.month = 0;
        this.year = 0;

        this.nonprofit = nonprofit;
        this.amount = amount;
    }

    public String getNonprofit() { return nonprofit; }
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getAmount() { return amount; }

    public void setNonprofit(String nonprofit) { this.nonprofit = nonprofit; }
    public void setDay(int day) { this.day = day; }
    public void setMonth(int month) { this.month = month; }
    public void setYear(int year) { this.year = year; }
    public void setAmount(double amount) { this.amount = amount; }


    public boolean isDateSet() {
        return(day != 0 || month != 0 || year != 0);
    }
}
