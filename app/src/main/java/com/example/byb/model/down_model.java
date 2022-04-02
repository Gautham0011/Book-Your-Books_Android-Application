package com.example.byb.model;

public class down_model {
    String name, url,numberofcopies,description,usn;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public down_model( String usn,String name, String description,String url, String numberofcopies ) {
        this.name = name;
        this.url = url;
        this.numberofcopies = numberofcopies;
        this.description = description;
        this.usn = usn;
    }

    public down_model(){

    }
}
