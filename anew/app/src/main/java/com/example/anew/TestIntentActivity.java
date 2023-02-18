package com.example.anew;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResultLauncher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestIntentActivity extends AppCompatActivity implements View.OnClickListener {
    public ActivityResultLauncher activityResultLauncher;
    private static final String TAG = "TestIntentAcitivity";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
        button = findViewById(R.id.btn);
        button.setOnClickListener(this);
        //测试活动结果启动器
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null){
                Bundle bundle1 = result.getData().getExtras();
                String s = bundle1.getString("tt");
                Log.d(TAG, "onCreate: 收到结果"+s);
                Toast.makeText(this,s,Toast.LENGTH_LONG).show();
            }
                });


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn){
            Intent intent = new Intent(this,MainActivity4.class);
            Bundle bundle = new Bundle();// 使用bundle传递消息
            bundle.putString("testLauncher","testActivityResultLauncher");
            Log.d(TAG, "onCreate: 传过去"+"testActivityResultLauncher");
            intent.putExtras(bundle);
            activityResultLauncher.launch(intent);
        }
    }
}