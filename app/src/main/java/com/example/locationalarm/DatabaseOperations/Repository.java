package com.example.locationalarm.DatabaseOperations;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.locationalarm.DataLoadListener;
import com.example.locationalarm.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Repository {
    private ArrayList<AlarmDetails> alarmDetails = new ArrayList<>();
    public static Repository instance;
    static Context mContext;
    static DataLoadListener dataLoadListener;
    public static Repository getInstance(Context context){
        mContext = context;
        if(instance == null) {
            instance = new Repository();
        }
        dataLoadListener =(DataLoadListener) mContext;
        return instance;
    }
    public MutableLiveData<ArrayList<AlarmDetails>> getAlarmDetails(){
        loadAlarms();
        MutableLiveData<ArrayList<AlarmDetails>> alarms = new MutableLiveData<>();
        alarms.setValue(alarmDetails);
        return alarms;
    }
    void loadAlarms(){
        AlarmDao alarmDao = new AlarmDao();
        alarmDao.getDatabaseReference().child("location").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                alarmDetails.add(snapshot.getValue(AlarmDetails.class));
                dataLoadListener.onDataLoaded();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
