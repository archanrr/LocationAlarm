package com.example.locationalarm.DatabaseOperations;

public class AlarmDetails {
    String key;
    double lat,lon;
    String Reason;
    AlarmDetails(String key, double lat,double lon,String reason){
        this.key = key;
        this.lat = lat;
        this.lon = lon;
        this.Reason = reason;
    }
    AlarmDetails(){

    }

    public String getKey() {
        return key;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getReason() {
        return Reason;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
