package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.anew.util.DateUtil;

import java.text.SimpleDateFormat;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button button;
    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.btn_one);
        buttonTest = findViewById(R.id.btn_test);
        //test1 设置公共点击事件
        button.setOnClickListener(this);
        //test2 点击按钮开启，关闭
        //test3 图像显示
    }

    //重写点击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_one){
            String desc = String.format("%s 您点击了按钮 %s"
                    , DateUtil.getNowTime(),button.getText());
            button.setText(desc);
        }
        if (v.getId() == R.id.btn_open){
            buttonTest.setTextColor(Color.GREEN);
            buttonTest.setEnabled(true);
            Toast.makeText(MainActivity2.this,"点击open",Toast.LENGTH_LONG).show();
        }
        if (v.getId() == R.id.btn_off){
            buttonTest.setTextColor(Color.GRAY);
            buttonTest.setEnabled(false);
            Toast.makeText(MainActivity2.this,"点击off",Toast.LENGTH_LONG).show();
        }
    }

}