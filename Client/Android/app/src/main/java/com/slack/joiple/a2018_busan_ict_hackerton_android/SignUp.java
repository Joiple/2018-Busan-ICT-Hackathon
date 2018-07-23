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
import android.widget.EditText;

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
    EditText idEdit,passEdit,nameEdit,ageEdit;
    Button cancelBtn,signUpBtn;
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
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idEdit.getText().toString(),password=passEdit.getText().toString(),name=nameEdit.getText().toString();
                String birth=ageEdit.getText().toString();
                //TODO send id,name,password,age to server for sign up
                SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("id",idEdit.getText().toString());
                editor.putString("pass",passEdit.getText().toString());
                editor.putString("name",nameEdit.getText().toString());
                editor.putString("age",ageEdit.getText().toString());
                editor.putBoolean("isUsing",true);
                editor.commit();
                o=new Intent(SignUp.this,MainActivity.class);
                String res="";
                new NetworkManager(getString(R.string.serverURL),res).execute("hello");
                Log.i("crawl",res);
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
}
