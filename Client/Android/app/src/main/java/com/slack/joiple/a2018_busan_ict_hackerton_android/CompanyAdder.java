package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CompanyAdder extends AppCompatActivity {
    NetworkManager manager;
    EditText name,address,number,description,location;
    Button searchBtn;
    TextView status;
    String networkReturn="";
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_add_company);
        //TODO connect with layout
        name=findViewById(R.id.nameEdit);
        address=findViewById(R.id.addressEdit);
        number=findViewById(R.id.crnEdit);
        description=findViewById(R.id.introductionEdit);
        location=findViewById(R.id.locationEdit);

        searchBtn=findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String compName=name.getText().toString(),
                        compAddress=address.getText().toString(),
                        compCrn=number.getText().toString(),
                        compIntroduction=description.getText().toString(),
                        compLocation=location.getText().toString();
                manager=new NetworkManager(getString(R.string.serverURL),"makecom");
                manager.in.addItem("name",compName);
                manager.in.addItem("location",compLocation);
                manager.in.addItem("address",compAddress);
                manager.in.addItem("crn",compCrn);
                manager.in.addItem("introduction",compIntroduction);
                if(manager.execute()){//TODO checking company adding
                        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("compName", compName);
                        editor.putString("compAddress", compAddress);
                }else{
                    //TODO get errCode from server
                    status.setText("");
                }
            }
        });

    }
}
