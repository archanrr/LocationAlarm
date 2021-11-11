package com.example.locationalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.locationalarm.DatabaseOperations.AlarmDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DataLoadListener {

    FragmentTransaction fragmentTransaction;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    AlarmViewModel alarmViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,new AlarmFragment());
        fragmentTransaction.commit();

        /*recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         */
        alarmViewModel = new ViewModelProvider(MainActivity.this).get(AlarmViewModel.class);
        alarmViewModel.init(MainActivity.this);
        alarmViewModel.getAlarmDetails().observe(this, new Observer<ArrayList<AlarmDetails>>() {
            @Override
            public void onChanged(ArrayList<AlarmDetails> alarmDetails) {
                startPrintLog(alarmDetails);
                Toast.makeText(MainActivity.this, "alarmDetails----->"+alarmDetails, Toast.LENGTH_SHORT).show();
                listAdapter.notifyDataSetChanged();
            }
        });

        listAdapter = new ListAdapter(alarmViewModel.getAlarmDetails().getValue());

        //recyclerView.setAdapter(listAdapter);

    }
    public void listAlarms(View v){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,new AlarmFragment());
        fragmentTransaction.commit();
    }
    void startPrintLog(ArrayList<AlarmDetails> alarmDetails){
        Log.d("Archan","size"+alarmDetails.size());
        for(int i=0;i<alarmDetails.size();i++) {
            Log.d("Archan",""+alarmDetails.get(i).getLat());
            Log.d("Archan",""+alarmDetails.get(i).getLon());
            Log.d("Archan",""+alarmDetails.get(i).getReason());
        }
        Toast.makeText(this, " reason"+alarmDetails, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataLoaded() {
        alarmViewModel.getAlarmDetails().observe(this, new Observer<ArrayList<AlarmDetails>>() {
            @Override
            public void onChanged(ArrayList<AlarmDetails> alarmDetails) {
                startPrintLog(alarmDetails);
                Toast.makeText(MainActivity.this, "alarmDetails"+alarmDetails, Toast.LENGTH_SHORT).show();
                listAdapter.notifyDataSetChanged();
            }
        });
    }
}