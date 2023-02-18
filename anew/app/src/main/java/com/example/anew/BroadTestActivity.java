package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anew.util.DateUtil;

public class BroadTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String ORDER_ACTION = "BroadTestActivity ORDER_ACTION";
    private static final String ALARM_ACTION = "ORDER_ACTION";
    private Button buttonAbort;
    private Button buttonSend;
    private TextView textView;
    private TextView textView1;
    private static final String TAG = "BroadTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_test);
        //广播例子
        buttonSend = findViewById(R.id.btn_send);
        buttonAbort = findViewById(R.id.btn_abort);
        textView = findViewById(R.id.tv_show);
        textView1 = findViewById(R.id.tv_show1);
        buttonSend.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        /*orderReceiverA = new OrderReceiverA();
        IntentFilter intentFilterA = new IntentFilter(ORDER_ACTION);
        intentFilterA.setPriority(8);
        registerReceiver(orderReceiverA, intentFilterA);

        orderReceiverB = new OrderReceiverB();
        IntentFilter intentFilterB = new IntentFilter(ORDER_ACTION);
        intentFilterB.setPriority(10);
        registerReceiver(orderReceiverB, intentFilterB);*/
        alarmRevice = new AlarmRevice();
        IntentFilter intentFilter = new IntentFilter(ALARM_ACTION);
        registerReceiver(alarmRevice,intentFilter);// 注册广播
    }

    public void sendAlarm() {
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        long delayTime = System.currentTimeMillis() + 3000;
        Log.d(TAG, "sendAlarm: ddddd"+delayTime);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,delayTime,pendingIntent);
        }else {
            alarmManager.set(AlarmManager.RTC_WAKEUP,delayTime,pendingIntent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send){
         /*   Intent intent = new Intent(ORDER_ACTION);
            sendOrderedBroadcast(intent,null);*/
            sendAlarm();
        }
    }

    private OrderReceiverB orderReceiverB;
    private class OrderReceiverB extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)){
                textView.setText(DateUtil.getNowTime()+"接收器B收到广播");
                if (buttonAbort.isClickable()) {
                    abortBroadcast();
                }
            }

        }
    }

    private OrderReceiverA orderReceiverA;
    private class OrderReceiverA extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)){
                textView1.setText(DateUtil.getNowTime()+":接收器A收到广播");
                if (buttonAbort.isClickable()) {
                    abortBroadcast();
                }
            }
        }
    }

    private AlarmRevice alarmRevice;
    private class AlarmRevice extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: intent"+intent.getAction());
            if (intent != null){
                textView1.setText("闹钟已到达"+DateUtil.getNowTime());
                Vibrator systemService = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                systemService.vibrate(5000);//震动若干秒
            }
        }
    }
}