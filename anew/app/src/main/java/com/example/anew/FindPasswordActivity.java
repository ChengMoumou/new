package com.example.anew;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class FindPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvPassword;
    private EditText edPassword;
    private Button btnForgetPassword;
    private CheckBox checkBox;
    private RadioButton rbPassword;
    private RadioButton rbVerfication;
    private ActivityResultLauncher activityResultLauncher;
    private static final String TAG = "FindPasswordActivity";
    private EditText edNum;
    private Boolean isCheckedOr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        RadioGroup radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioListener());
        tvPassword = findViewById(R.id.tv_login_password);
        edPassword = findViewById(R.id.ed_login_password);
        edNum = findViewById(R.id.ed_num);
        btnForgetPassword = findViewById(R.id.btn_forget_password);
        checkBox = findViewById(R.id.ck_password);
        rbPassword = findViewById(R.id.rb_password);
        rbVerfication = findViewById(R.id.rb_verification);
        rbPassword.setChecked(true);
        //??????????????????
        edPassword.addTextChangedListener(new HideTextWatcher(edPassword,6));
        edNum.addTextChangedListener(new HideTextWatcher(edNum,11));

        checkBox.setOnCheckedChangeListener(new MyCheckListener());

        btnForgetPassword.setOnClickListener(this);//????????????

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            //????????????????????????
            if (result.getResultCode() == RESULT_OK && result.getData()!=null){
                Bundle extras = result.getData().getExtras();
                String password = extras.getString("password");
                Log.d(TAG, "onCreate: password"+password);
                Toast.makeText(FindPasswordActivity.this,password,Toast.LENGTH_LONG).show();
            }
                }
                );
    }

    public class RadioListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_password){//????????????
                tvPassword.setText("????????????");
                edPassword.setHint("???????????????");
                btnForgetPassword.setText("????????????");
                checkBox.setVisibility(View.VISIBLE);
            }else if (checkedId == R.id.rb_verification){//???????????????
                tvPassword.setText("?????????");
                edPassword.setHint("??????????????????");
                btnForgetPassword.setText("???????????????");
                checkBox.setVisibility(View.GONE);
            }
        }
    }
    public class HideTextWatcher implements TextWatcher{
        private EditText view;
        private int maxlengths;
        public HideTextWatcher(EditText mview,int maxLength) {
                super();
                view = mview;
                maxlengths = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //s??????????????????
        @Override
        public void afterTextChanged(Editable s) {
            String st = s.toString();
            if ((st.length() == 11 &&  maxlengths == 11)|| (st.length() == 6 && maxlengths== 6)){
                Log.d(TAG, "afterTextChanged: st"+st.length());
                //????????????
                InputMethodManager inputMethodManager = (InputMethodManager)FindPasswordActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

            }
        }
    }

    public class MyCheckListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(FindPasswordActivity.this,"?????????"+isChecked,Toast.LENGTH_LONG).show();
            isCheckedOr = isChecked;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        edPassword.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_forget_password){
            if (rbPassword.isChecked()){//???????????????????????????????????????
                Intent intent = new Intent(this,ForgetPasswordActivity.class);
                intent.putExtra("phone",edPassword.getText());
                activityResultLauncher.launch(intent);//?????????????????????
                Log.d(TAG, "onClick: runNextPage");
            }else if (rbVerfication.isChecked()){//?????????????????????????????????????????????
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("??????????????????");
                builder.setMessage("????????????12345465");
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FindPasswordActivity.this,"???????????????????????????",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        }
    }
}