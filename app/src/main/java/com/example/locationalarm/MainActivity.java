package com.example.locationalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.locationalarm.DatabaseOperations.AlarmDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AlarmViewListener{

    FragmentTransaction fragmentTransaction;
    RecyclerView recyclerView;
    FloatingActionButton fabAddAlarm,fabRefreshAlarm;
    ListAdapter listAdapter;
    AlarmViewModel alarmViewModel;
    ArrayList<AlarmDetails> alarms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alarms);
        initView();
        setListeners();
        setupObservers();
        alarmViewModel.getAlarms();
    }

    public void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        fabAddAlarm = findViewById(R.id.fab_new_alarm);
        fabRefreshAlarm = findViewById(R.id.fab_refresh);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmViewModel = new ViewModelProvider(MainActivity.this).get(AlarmViewModel.class);
        listAdapter = new ListAdapter(alarms, this);
        recyclerView.setAdapter(listAdapter);
    }

    public void setupObservers() {
        alarmViewModel.getAlarmDetails().observe(this, alarmDetails -> {
            alarms.clear();
            alarms.addAll(alarmDetails);
            //Toast.makeText(MainActivity.this, "alarmDetails----->"+alarmDetails, Toast.LENGTH_SHORT).show();
            listAdapter.notifyDataSetChanged();
        });
    }

    public void setListeners() {
        fabAddAlarm.setOnClickListener(v -> {
            Intent intent = new Intent(this,AddAlarmActivity.class);
            startActivity(intent);
        });
        fabRefreshAlarm.setOnClickListener(v -> {
            onRefreshClick();
        });
    }

    @Override
    public void onAlarmClick(AlarmDetails alarmDetails, Integer position) {

    }

    @Override
    public void onCancelClick(AlarmDetails alarmDetails, Integer position) {
        alarmViewModel.cancelAlarm(alarmDetails.getKey());
        onRefreshClick();
    }

    @Override
    public void onRefreshClick() {
        alarmViewModel.refreshScreen();
    }
}