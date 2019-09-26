package com.blurrays.myjobapp.Classes;



import android.graphics.Color;

import java.io.Serializable;

public class Workfloor implements Serializable {
    private String companyId;
    private String name;
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Workfloor(){

    }

    public Workfloor(String companyId, String name, int alpha, int red, int green, int blue) {
        this.companyId = companyId;
        this.name = name;
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
