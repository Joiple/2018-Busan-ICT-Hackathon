package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CompanyAdder extends AppCompatActivity {
    NetworkManager manager;
    EditText name,address,number,description,phone,location;
    String networkReturn="";
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_add_company);
        //TODO connect with layout
        manager=new NetworkManager(getString(R.string.serverURL),networkReturn);
    }
}
