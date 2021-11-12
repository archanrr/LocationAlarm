package com.example.locationalarm.DatabaseOperations;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmDao {
    FirebaseDatabase db;
    DatabaseReference mRef;
    public AlarmDao(){
        db= FirebaseDatabase.getInstance();
        mRef=  db.getReference();
    }
    public void addAlarmData(Double lat,Double lon,String Reason){
        String key = mRef.child("location").push().getKey();
        AlarmDetails alarmDetails = new AlarmDetails(key,lat,lon,Reason);
        mRef.child("location").child(key).setValue(alarmDetails);
    }


    public DatabaseReference getDatabaseReference() {
        return mRef;
    }


}