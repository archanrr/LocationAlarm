package com.example.locationalarm.DatabaseOperations;

import com.example.locationalarm.AlarmViewModel;

public class AlarmDetails {
    double lat,lon;
    String Reason;
    AlarmDetails(double lat,double lon,String reason){
        this.lat = lat;
        this.lon = lon;
        this.Reason = reason;
    }
    AlarmDetails(){

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
}
