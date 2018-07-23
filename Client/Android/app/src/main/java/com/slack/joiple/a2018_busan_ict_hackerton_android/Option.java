package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class Option extends AppCompatActivity {
    ToggleButton mainRead;
    Button logOutBtn;
    Intent o,g;
    SharedPreferences user,settings;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_option);
        g=getIntent();
        user =getSharedPreferences("user",MODE_PRIVATE);
        settings=getSharedPreferences("settings",MODE_PRIVATE);
        mainRead=findViewById(R.id.mainReadBtn);
        logOutBtn=findViewById(R.id.logOutBtn);
        mainRead.setChecked(settings.getBoolean("mode",true));
        mainRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor=settings.edit();
                if(b){
                    editor.putBoolean("mode",true);
                }else{
                    editor.putBoolean("mode",false);
                }
                editor.commit();
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor= user.edit();
                editor.putBoolean("isUsing",false);
                editor.commit();
                o=new Intent("logout");
                setResult(RESULT_OK,o);
                finish();
            }
        });
    }
}
