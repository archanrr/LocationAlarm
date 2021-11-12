package com.example.locationalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.locationalarm.DatabaseOperations.AlarmDao;

public class AddAlarmActivity extends AppCompatActivity {

    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        addBtn = findViewById(R.id.add_alarm_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDao alarmDao = new AlarmDao();
                EditText lat = findViewById(R.id.lat);
                EditText lon = findViewById(R.id.lon);
                EditText reason = findViewById(R.id.reason);
                alarmDao.addAlarmData(Double.parseDouble(lat.getText().toString()),Double.parseDouble(lon.getText().toString()),reason.getText().toString());
            }
        });
    }
}