package com.example.byb.HomeModel;



import android.graphics.drawable.GradientDrawable;

public class modelclass {

    int image;
    String title;
    GradientDrawable color;

    public modelclass(GradientDrawable color, int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }





}
