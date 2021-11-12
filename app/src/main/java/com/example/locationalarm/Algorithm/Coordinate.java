package com.example.locationalarm.Algorithm;

import java.util.ArrayList;

public class Coordinate{
    double lat,lon;
    double distance = Double.MAX_VALUE;
    ArrayList<Coordinate> nearbyList;
    public Coordinate(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        this.left = null;
        this.right = null;
        this.nearbyList = new ArrayList<>();
    }
    Coordinate left;
    Coordinate right;
    void updateDistance(double newDist){
        this.distance = newDist;
    }
}
