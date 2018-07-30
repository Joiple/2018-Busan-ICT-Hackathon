package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Initial extends AppCompatActivity {
    Intent o;
    SharedPreferences pref;

    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_initial);

        pref=getSharedPreferences("user",MODE_PRIVATE);
        boolean isUsing=pref.getBoolean("isUsing",false);
        if(isUsing){
            o=new Intent(Initial.this,MainActivity.class);
            startActivity(o);
        }else{
            o=new Intent(Initial.this,SignIn.class);
            startActivity(o);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();

    }

}
