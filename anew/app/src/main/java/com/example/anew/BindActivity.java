package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anew.util.DateUtil;

public class BindActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView textView;
    private Button buttonStartBind;
    private Button buttonUnBind;
    private static String  des;
    private Intent intent;
    private Button buttonRespone;
    private static final String TAG = "BindActivity";
    private MyService.LocalIbinder ibinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        textView = findViewById(R.id.tv_imme);
 findViewById(R.id.btn_start_bind).setOnClickListener(this);
         findViewById(R.id.btn_unbind).setOnClickListener(this);
//        findViewById(R.id.btn_respone).setOnClickListener(this);
        des = "";
        intent = new Intent(this,MyService.class);//创建绑定意图
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_bind){
            Log.d(TAG, "onClick: ");
            startService(intent);
            boolean b = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }else if (v.getId() == R.id.btn_unbind){
            if (myService != null){
                unbindService(serviceConnection);
//                stopService(intent);
                myService = null;
            }
        }
    }
    //textview展示文字
    public static void showText(String desc){
        des = String.format("%s%s %s\n", des, DateUtil.getNowTime(),desc);
        Log.d(TAG, "showText: des"+des);
        textView.setText(des);
    }

    private MyService myService; //声明一个服务对象
    private ServiceConnection serviceConnection = new ServiceConnection() {
        //获取到服务对象的操作
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            myService =((MyService.LocalIbinder) service).getService();
            ibinder = (MyService.LocalIbinder) service;
            String count = ibinder.gerCount(100);

            Log.d(TAG, "onServiceConnected"+count);
        }

        //无法获得服务对象
        @Override
        public void onServiceDisconnected(ComponentName name) {
            ibinder = null;
            Log.d(TAG, "onServiceDisconnected");
        }
    };


}