package com.example.locationalarm;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.locationalarm.Algorithm.BinaryTree;
import com.example.locationalarm.Algorithm.Coordinate;
import com.example.locationalarm.DatabaseOperations.AlarmDao;
import com.example.locationalarm.DatabaseOperations.AlarmDetails;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class AlarmViewModel extends ViewModel {

    private MutableLiveData<ArrayList<AlarmDetails>> allAlarmDetails = new MutableLiveData<>();
    BinaryTree binaryTree = new BinaryTree();
    AlarmDao alarmDao = new AlarmDao();

    public void getAlarms() {
        alarmDao.getDatabaseReference().child("location").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                ArrayList<AlarmDetails> alarms = new ArrayList<>();
                while (iterator.hasNext()) {
                    alarms.add(iterator.next().getValue(AlarmDetails.class));
                }
                allAlarmDetails.postValue(alarms);
                startLoadingData(alarms);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void cancelAlarm(String keyToDelete) {
        alarmDao.getDatabaseReference().child("location").child(keyToDelete).removeValue();
    }

    private void startLoadingData(ArrayList<AlarmDetails> alarmDetails){
        if(alarmDetails == null) return;
        AlarmDetails root = alarmDetails.get(0);
        binaryTree.setRoot(new Coordinate(root.getLat(),root.getLat()));
        for(int i=1;i<alarmDetails.size();i++) {
            AlarmDetails temp = alarmDetails.get(i);
            binaryTree.InsertData(root,new Coordinate(temp.getLat(),temp.getLat()));
        }
        findNearByLocation(new Coordinate(root.getLat(),root.getLat()));
    }

    private void findNearByLocation(Coordinate coordinate){
        binaryTree.printData(coordinate);
    }

    public LiveData<ArrayList<AlarmDetails>> getAlarmDetails(){
        return allAlarmDetails;
    }

    public void refreshScreen() {
        getAlarms();
    }
}
