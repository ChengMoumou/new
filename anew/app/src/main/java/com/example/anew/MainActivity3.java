package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.anew.util.DateUtil;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {
    Button button;
    private static final String TAG = "Cannot invoke method length() on null object";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        findViewById(R.id.btn).setOnClickListener(this);
//        button.setOnClickListener(new MyOnclickListen());
    }
/*    class MyOnclickListen implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String des = String.format("%s你点击了按钮%s",
                    DateUtil.getNowTime(),button.getText());
            button.setText(des);
            startActivity(new Intent(this,MainActivity4.class));
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn){
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                    Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
            datePickerDialog.show();

            new TimePickerDialog(this,this,
                    Calendar.HOUR_OF_DAY,Calendar.MINUTE,true).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: year"+year+"month"+month+"dayOfMonth"+dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}