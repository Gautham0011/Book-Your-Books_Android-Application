package com.example.byb.model;

public class upload_pdf {
    public String name,url,numberofcopies,description,usn;
    public upload_pdf(){

    }

    public upload_pdf(String name, String url, String numberofcopies, String description, String usn) {
        this.name = name;
        this.url = url;
        this.numberofcopies = numberofcopies;
        this.description = description;
        this.usn = usn;
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
}
