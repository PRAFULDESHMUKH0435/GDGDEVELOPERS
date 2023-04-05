package com.example.gdg.Models;

public class PastModel {
    String date,name,url,venue;

    public PastModel ( ) {
    }

    public PastModel (String date, String name, String url, String venue) {
        this.date = date;
        this.name = name;
        this.url = url;
        this.venue = venue;
    }

    public String getDate ( ) {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getUrl ( ) {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getVenue ( ) {
        return venue;
    }

    public void setVenue (String venue) {
        this.venue = venue;
    }
}
