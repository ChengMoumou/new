package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity4";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        button = findViewById(R.id.btn_nine);
        button.setOnClickListener(this);
        Log.d(TAG, "onCreate: ...........................................................................");
        //接收到的内容
        Bundle extras = getIntent().getExtras();
        String testLauncher = extras.getString("testLauncher");
        Toast.makeText(this,"TestActivity传过来"+testLauncher,Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate: TestActivity传过来\"+testLauncher"+testLauncher);


        

    }
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
        if (v.getId() == R.id.btn_nine){
            //传回去
            Intent putAgainIntent = new Intent(this,TestIntentActivity.class);
            putAgainIntent.putExtra("tt","main4传回去的");
            Log.d(TAG, "onCreate: 传回去tt");
            setResult(RESULT_OK,putAgainIntent);
        }
    }
}