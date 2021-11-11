package com.example.locationalarm.DatabaseOperations;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlarmDao {
    FirebaseDatabase db;
    DatabaseReference mRef;
    public AlarmDao(){
        db= FirebaseDatabase.getInstance();
        mRef=  db.getReference();
    }
    public void addAlarmData(Double lat,Double lon,String Reason){
        AlarmDetails alarmDetails = new AlarmDetails(lat,lon,Reason);
        mRef.child("location").push().setValue(alarmDetails);
    }

    public DatabaseReference getDatabaseReference() {
        return mRef;
    }
    public List<AlarmDetails> getAlarmData(){
        mRef.child("location").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of locations in datasnapshot
                        Map<String,Object> value =  (Map<String,Object>)dataSnapshot.getValue();
                        for (Map.Entry<String, Object> entry : value.entrySet()){

                            //Get user map
                            Map oneLocation = (Map) entry.getValue();
                            //double temp = (double) oneLocation.get("lat");
                            Double lat = (Double) oneLocation.get("lat");
                            Double lon = (Double) oneLocation.get("lon");
                            String reason = (String) oneLocation.get("reason");
                            //.postValue(new AlarmDetails(lat,lon,reason));
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        return null;
    }

    private void readFullLocationData(Map<String, Object> value,MutableLiveData<List<AlarmDetails>> list) {
        //list = new ArrayList<>();
        //iterate through each location, ignoring their UID
        if(value==null) return;

        Log.d("Archan","lat"+list);
        //Toast.makeText(, "", Toast.LENGTH_SHORT).show();

    }

}