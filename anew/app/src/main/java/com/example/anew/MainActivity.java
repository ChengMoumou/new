package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anew.util.Utils;


public class MainActivity extends AppCompatActivity {
public final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        boolean postDelayed = new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(MainActivity.this, MainActivity2.class));
//            }
//        }, 3000);
//        Log.d(TAG, "onCreate: postDelayed"+postDelayed);
//        TextView textView = findViewById(R.id.tv);
//        textView.setTextSize(30);
        TextView textView = findViewById(R.id.tv);
        //获取tv的布局参数
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        // dp转px，默认是px为单位
        layoutParams.width = Utils.dip2px(this,200f);
        layoutParams.height = Utils.dip2px(this,200f);
        textView.setLayoutParams(layoutParams);
    }
}