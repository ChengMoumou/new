package com.example.anew;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public IBinder iBinder = new LocalIbinder();//创建一个粘合剂对象
    private static final String TAG = "MyService";
    public MyService() {
    }

    public class LocalIbinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }

        public String gerCount(int count){
            return "我收到了数字"+count;
        }
    }

    public void reFresh(String s){
        Log.d(TAG, "reFresh: s"+s);
        BindActivity.showText(s);
    }
    @Override
    public IBinder onBind(Intent intent) {
        reFresh("onBind");
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        reFresh("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        reFresh("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        reFresh("onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        reFresh("onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        reFresh("onRebind");
    }
}