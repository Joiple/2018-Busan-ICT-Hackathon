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
    EditText name,address,number,description,phone,location;
    Button searchBtn;
    TextView status;
    String networkReturn="";
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_add_company);
        //TODO connect with layout
        manager=new NetworkManager(getString(R.string.serverURL));
        searchBtn=findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true){//TODO checking company adding
                    //TODO adding Company to server;
                    SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putString("compName",name.getText().toString());
                    editor.putString("compAddress",address.getText().toString());
                }else{
                    //TODO get errCode from server
                    status.setText("");
                }
            }
        });
    }
}
