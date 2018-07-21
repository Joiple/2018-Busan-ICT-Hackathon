package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    Button changeComp;
    TextView name,company;
    Intent o,g;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_profile);
        name=findViewById(R.id.nameView);
        company=findViewById(R.id.companyView);
        changeComp=findViewById(R.id.changeCompBtn);
        changeComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(Profile.this,ChangeCompany.class);
                startActivityForResult(o,0);
            }
        });
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        if(result==RESULT_CANCELED)return;
        String action=data.getAction();
        if(action.equals("changeComp")){
            //TODO get company name from sharedPreferences
        }
    }
}
