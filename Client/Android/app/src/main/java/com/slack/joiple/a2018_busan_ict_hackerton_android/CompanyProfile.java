package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CompanyProfile extends AppCompatActivity {
    TextView nameView,locationView,addressView,crnView,introductionView;
    EditText nameEdit,locationEdit,addressEdit,crnEdit,introductionEdit;
    Button editBtn,confirmBtn,cancelBtn,backBtn;
    NetworkManager nm;
    boolean changed=false;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_company_profile);

        nameView=findViewById(R.id.nameView);
        locationView=findViewById(R.id.locationView);
        addressView=findViewById(R.id.addressView);
        crnView=findViewById(R.id.crnView);
        introductionView=findViewById(R.id.introductionView);
        nameEdit=findViewById(R.id.nameEdit);
        locationEdit=findViewById(R.id.locationEdit);
        addressEdit=findViewById(R.id.addressEdit);
        crnEdit=findViewById(R.id.crnEdit);
        introductionEdit=findViewById(R.id.introductionEdit);
        editBtn=findViewById(R.id.editBtn);
        confirmBtn=findViewById(R.id.confirmBtn);
        cancelBtn=findViewById(R.id.cancelBtn);
        backBtn=findViewById(R.id.backBtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutEditMode();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO change proper action nm=new NetworkManager(getString(R.string.serverURL),"post");
                SharedPreferences.Editor edit=getSharedPreferences("comp",MODE_PRIVATE).edit();
                edit.putString("name",nameEdit.getText().toString());
                edit.putString("address",addressEdit.getText().toString());
                edit.putString("location",locationEdit.getText().toString());
                edit.putString("crn",crnEdit.getText().toString());
                edit.putString("introduction",introductionEdit.getText().toString());
                edit.commit();
                //nm.execute();
                
                changed=true;
                setTextsOnNetwork();
                layoutViewMode();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTextsOnNetwork();
                layoutViewMode();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setTextsOnNetwork();

    }

    @Override
    public void onBackPressed(){
        if(changed){
            setResult(RESULT_OK);
        }
        super.onBackPressed();
    }
    public void setTextsOnNetwork(){
        nm=new NetworkManager(getString(R.string.serverURL),"post");//TODO change action properly
        nm.in.addItem("code","");
        //nm.execute();
        //
        SharedPreferences preff=getSharedPreferences("comp",MODE_PRIVATE);
        nm.out.addItem("name",preff.getString("name","null"));
        nm.out.addItem("location",preff.getString("location","null"));
        nm.out.addItem("address",preff.getString("address","null"));
        nm.out.addItem("crn",preff.getString("crn","null"));
        nm.out.addItem("introduction",preff.getString("introduction","null"));
        //
        String name=nm.out.getItem("name");
        String location=nm.out.getItem("location");
        String address=nm.out.getItem("address");
        String crn=nm.out.getItem("crn");
        String introduction=nm.out.getItem("introduction");
        nameView.setText(name);
        nameEdit.setText(name);
        locationView.setText(location);
        locationEdit.setText(location);
        addressView.setText(address);
        addressEdit.setText(address);
        crnView.setText(crn);
        crnEdit.setText(crn);
        introductionView.setText(introduction);
        introductionEdit.setText(introduction);
    }
    public void layoutEditMode(){
        nameView.setVisibility(View.GONE);
        addressView.setVisibility(View.GONE);
        locationView.setVisibility(View.GONE);
        crnView.setVisibility(View.GONE);
        introductionView.setVisibility(View.GONE);
        nameEdit.setVisibility(View.VISIBLE);
        addressEdit.setVisibility(View.VISIBLE);
        locationEdit.setVisibility(View.VISIBLE);
        crnEdit.setVisibility(View.VISIBLE);
        introductionEdit.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);
        confirmBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }
    public void layoutViewMode(){
        nameView.setVisibility(View.VISIBLE);
        addressView.setVisibility(View.VISIBLE);
        locationView.setVisibility(View.VISIBLE);
        crnView.setVisibility(View.VISIBLE);
        introductionView.setVisibility(View.VISIBLE);
        nameEdit.setVisibility(View.GONE);
        addressEdit.setVisibility(View.GONE);
        locationEdit.setVisibility(View.GONE);
        crnEdit.setVisibility(View.GONE);
        introductionEdit.setVisibility(View.GONE);
        cancelBtn.setVisibility(View.GONE);
        confirmBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

}