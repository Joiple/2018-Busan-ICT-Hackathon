package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Option extends AppCompatActivity {
    ToggleButton mainRead;
    Button logOutBtn;
    Intent o,g;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_option);
        g=getIntent();
        mainRead=findViewById(R.id.mainReadBtn);
        logOutBtn=findViewById(R.id.logOutBtn);
        mainRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){//TODO QR

                }else{//TODO NFC

                }
            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO logout
                o=new Intent("logout");
                setResult(RESULT_CANCELED,o);
                finish();
            }
        });
    }
}
