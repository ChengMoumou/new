package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText editText;
    private static final String TAG = "ForgetPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editText = findViewById(R.id.et_password_first);
        Button button = findViewById(R.id.btn_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: onclick");
                Intent intent = new Intent();
                intent.putExtra("password","123456");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}