package com.example.locationalarm;

import com.example.locationalarm.DatabaseOperations.AlarmDetails;

public interface AlarmViewListener {
    public void onAlarmClick(AlarmDetails alarmDetails, Integer position);
    public void onCancelClick(AlarmDetails alarmDetails, Integer position);
    public void onRefreshClick();

}
