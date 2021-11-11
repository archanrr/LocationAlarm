package com.example.locationalarm;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.locationalarm.DatabaseOperations.AlarmDetails;
import com.example.locationalarm.DatabaseOperations.Repository;

import java.util.ArrayList;

public class AlarmViewModel extends ViewModel {
    private MutableLiveData<ArrayList<AlarmDetails>> allAlarmDetails;
    void init(Context context){
        if(allAlarmDetails != null) return;
        allAlarmDetails = Repository.getInstance(context).getAlarmDetails();
    }
    public LiveData<ArrayList<AlarmDetails>> getAlarmDetails(){
        return allAlarmDetails;
    }
}
