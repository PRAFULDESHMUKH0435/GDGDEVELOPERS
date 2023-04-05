package com.example.gdg.Models;

public class UpcomingModel {

    String name,url,venue,date;

    public UpcomingModel ( ) {
    }

    public UpcomingModel (String name, String url, String venue,String date) {
        this.name = name;
        this.url = url;
        this.venue = venue;
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

    public String getDate ( ) {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }
}
