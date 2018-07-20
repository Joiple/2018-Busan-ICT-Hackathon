package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Initial extends AppCompatActivity {
    Intent o;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_initial);
        if(true){//TODO user information check result->true : information exist
            o=new Intent(Initial.this,MainActivity.class);
            startActivity(o);
        }else{
            o=new Intent(Initial.this,SignIn.class);
            startActivity(o);
        }

        finish();
    }
}
