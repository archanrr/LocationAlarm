package com.example.locationalarm.Algorithm;
//To Store the Coordinates as Tree based on Distance Still working on.
public class BinaryTree {
    Coordinate root;
    static int minDistance = 5;
    void InsertData(Coordinate root,Coordinate newCoordinate) {
        if(root == null) {
            return;
        }
        Coordinate head = root;
        Coordinate curr = head;
        double distance = 0;
        while(head!=null) {
            curr = head;
            distance = MapApi.getDistanceFromLatLonInKm(newCoordinate.lat, newCoordinate.lon,root.lat, root.lon);
            if(distance <= minDistance) {
                System.out.println("distance minimum"+distance);
                root.nearbyList.add(newCoordinate);
                return;
            }
            if(distance > root.distance) {
                System.out.println("distance left"+distance);
                head = head.right;
            } else {
                System.out.println("distance right"+distance);
                head = head.left;
            }
        }
        if(curr.distance < distance) {
            curr.right = newCoordinate;
            curr.right.distance = distance;
        } else {
            curr.left = newCoordinate;
            curr.left.distance = distance;
        }
    }

    void printData( Coordinate root) {
        if(root == null) {
            return;
        }

        printData(root.left);

        System.out.println("Distance"+root.distance);
        System.out.println(" Latititude "+root.lat+" Longititude "+root.lon);

        printData(root.right);
    }
    Coordinate newCoordinateFindNearestCoordinate(Coordinate root,Coordinate newCoordinate) {
        if(root == null) {
            return null;
        }
        Coordinate head = root;
        Coordinate curr = head;
        double distance = 0;
        while(head!=null) {
            curr = head;
            distance = MapApi.getDistanceFromLatLonInKm(newCoordinate.lat, newCoordinate.lon,head.lat, head.lon);
            if(distance <= minDistance) {
                int i=0;
                Coordinate ret = curr;
                int size = curr.nearbyList.size();
                double minimumDist = Double.MAX_VALUE;
                while(i<size) {
                    Coordinate fromList = curr.nearbyList.get(i);
                    distance = MapApi.getDistanceFromLatLonInKm(newCoordinate.lat, newCoordinate.lon,fromList.lat, fromList.lon);
                    if(distance< minimumDist) {
                        minimumDist = distance;
                        ret = fromList;
                    }
                }
                return ret;
            }
            if(distance > root.distance) {
                head = head.right;
            } else {
                head = head.left;
            }
        }

        return curr;
    }
}
