package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Initial extends AppCompatActivity {
    Intent i;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_initial);
        if(true){//TODO user information check result->true : information exist
            i=new Intent(Initial.this,MainActivity.class);
            startActivity(i);
        }else{
            i=new Intent(Initial.this,SignIn.class);
            startActivity(i);
        }

        finish();
    }
}
