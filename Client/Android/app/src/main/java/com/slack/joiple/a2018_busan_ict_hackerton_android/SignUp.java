package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SignUp extends AppCompatActivity {
    Intent o,g;
    EditText idEdit,passEdit,nameEdit,emailEdit,wageEdit,phoneEdit;
    Button cancelBtn,signUpBtn;
    DatePicker ageEdit;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_sign_up);
        g=getIntent();
        idEdit=findViewById(R.id.idEdit);
        passEdit=findViewById(R.id.passEdit);
        nameEdit=findViewById(R.id.nameEdit);
        ageEdit=findViewById(R.id.ageEdit);
        cancelBtn=findViewById(R.id.cancelBtn);
        signUpBtn=findViewById(R.id.signUpBtn);
        emailEdit=findViewById(R.id.emailEdit);
        wageEdit=findViewById(R.id.wageEdit);
        phoneEdit=findViewById(R.id.phoneEdit);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send id,name,password,age to server for sign up
                SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                String id=idEdit.getText().toString(),password=passEdit.getText().toString(),name=nameEdit.getText().toString();
                String birth=ageEdit.getYear()+"-"+(ageEdit.getMonth()+1)+"-"+ageEdit.getDayOfMonth();
                String email=emailEdit.getText().toString(),wage=wageEdit.getText().toString(),phone=phoneEdit.getText().toString();

                editor.putString("id",id);
                editor.putString("pass",password);
                editor.putString("name",name);
                editor.putString("birth",birth);
                editor.putBoolean("isUsing",true);
                editor.commit();
                String result="null";
                NetworkManager nm=new NetworkManager(getString(R.string.serverURL),"post");
                
                nm.in.addItem("id",id);
                nm.in.addItem("password",password);
                nm.in.addItem("name",name);
                nm.in.addItem("birthday",birth);
                nm.in.addItem("email",email);
                nm.in.addItem("wage",wage);
                nm.in.addItem("phone_number",phone);
                nm.in.addItem("company","");
                nm.execute();
                Log.d("result",nm.out.map.toString());
                o=new Intent(SignUp.this,MainActivity.class);
                startActivity(o);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "종료하실려면 한번더 뒤로가기를 누르세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
