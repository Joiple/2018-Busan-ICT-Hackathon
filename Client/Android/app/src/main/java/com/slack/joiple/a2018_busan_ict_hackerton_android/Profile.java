package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Network;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    Button changeComp;
    TextView name,company;
    Intent o,g;
    SharedPreferences pref;
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
                o=new Intent(Profile.this,CompanyChanger.class);
                startActivityForResult(o,0);
            }
        });
        pref=getSharedPreferences("user",MODE_PRIVATE);
        NetworkManager nw=new NetworkManager(getString(R.string.serverURL));
        nw.in.addItem("id",pref.getString("id","null"));
        nw.in.addItem("password",pref.getString("password","null"));

    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        if(result==RESULT_CANCELED)return;
        String action=data.getAction();
        if(action.equals("changeComp")){
            pref.getString("compName","null");
        }
    }
}
