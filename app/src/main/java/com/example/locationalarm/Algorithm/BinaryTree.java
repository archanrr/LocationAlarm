package com.example.locationalarm.Algorithm;
//To Store the Coordinates as Tree based on Distance Still working on.
// this algo is based on algorithm.
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
            //Checking the difference between current root and new Coordinate.
            distance = MapApi.getDistanceFromLatLonInKm(newCoordinate.lat, newCoordinate.lon,root.lat, root.lon);
            //Checking the distance are in range ir less than 0.5KM
            // if so add the new coordinate to nearby list to this point.
            //to Searching time will drastically reduced
            if(distance <= minDistance) {
                System.out.println("distance minimum"+distance);
                root.nearbyList.add(newCoordinate);
                return;
            }
            // If new coordinate is not in range defines,
            // add it as new node in tree with distance between smallest node in tree.
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
    // Find the nearest Coordinate from root.
    Coordinate newCoordinateFindNearestCoordinate(Coordinate root,Coordinate newCoordinate) {
        if(root == null) {
            return null;
        }
        Coordinate head = root;
        Coordinate curr = head;
        double distance = 0;
        while(head!=null) {
            curr = head;
            //Check the distance b/w current node and current location
            distance = MapApi.getDistanceFromLatLonInKm(newCoordinate.lat, newCoordinate.lon,head.lat, head.lon);
            //if distance is less the it will be present in ArrayList
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
            // Otherwise it would have been as other node.
            if(distance > root.distance) {
                head = head.right;
            } else {
                head = head.left;
            }
        }
        // Cureent point is not availble in tree
        return null;
    }
}
