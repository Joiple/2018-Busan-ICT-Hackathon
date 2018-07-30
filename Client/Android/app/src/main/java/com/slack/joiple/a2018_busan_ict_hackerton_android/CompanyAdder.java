package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CompanyAdder extends AppCompatActivity {
    NetworkManager manager;
    Intent o;
    EditText name,address,number,description,location;
    Button addBtn,cancelBtn;
    ImageButton menu, logout, option, logofont;
    TextView status;
    SharedPreferences user;
    SharedPreferences.Editor editor;
    String networkReturn="";
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_add_company);
        //TODO connect with layout
        user=getSharedPreferences("user",MODE_PRIVATE);
        name=findViewById(R.id.nameEdit);
        address=findViewById(R.id.addressEdit);
        number=findViewById(R.id.crnEdit);
        description=findViewById(R.id.introductionEdit);
        location=findViewById(R.id.locationEdit);
        menu=findViewById(R.id.slideBtn);
        logout=findViewById(R.id.logoutBtn);
        option=findViewById(R.id.statusBtn);
        logofont=findViewById(R.id.logofontBtn);
        addBtn=findViewById(R.id.addBtn);
        cancelBtn=findViewById(R.id.cancelBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
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
                if(true){//TODO checking company adding->manager.execute()
                        SharedPreferences pref = getSharedPreferences("comp", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("name", compName);
                        editor.putString("address", compAddress);
                        editor.putString("crn",compCrn);
                        editor.putString("location",compCrn);
                        editor.putString("introduction",compIntroduction);
                        editor.putBoolean("isHost",true);
                        editor.commit();
                        setResult(RESULT_OK);
                        finish();
                }else{
                    //TODO get errCode from server
                    status.setVisibility(View.VISIBLE);
                    status.setText("error occurred. please check your data.");
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor= user.edit();
                editor.putBoolean("isUsing",false);
                editor.commit();
                o=new Intent(CompanyAdder.this,SignIn.class);
                startActivity(o);
                finish();
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(CompanyAdder.this,Option.class);
                startActivityForResult(o,0);
            }
        });
        logofont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(CompanyAdder.this,MainActivity.class);
                startActivityForResult(o,0);
                setResult(RESULT_OK);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onActivityResult(int request,int result,Intent data){
        switch(request){
            case 0:
                if(result==RESULT_OK) finish();
                break;
        }
    }
}
