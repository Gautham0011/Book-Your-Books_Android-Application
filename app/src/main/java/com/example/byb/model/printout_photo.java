package com.example.byb.model;

public class printout_photo {
    private String numberofcopies, description, usn, image, category, pid, date, time;

    public printout_photo()
    {

    }

    public printout_photo(String numberofcopies, String description, String usn, String image, String category, String pid, String date, String time) {
        this.numberofcopies = numberofcopies;
        this.description = description;
        this.usn = usn;
        this.image = image;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
    }

    public String getNumberofcopies() {
        return numberofcopies;
    }

    public void setNumberofcopies(String numberofcopies) {
        this.numberofcopies = numberofcopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
