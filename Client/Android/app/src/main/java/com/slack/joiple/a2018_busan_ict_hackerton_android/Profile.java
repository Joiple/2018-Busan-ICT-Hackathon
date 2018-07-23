package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Network;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Profile extends AppCompatActivity {// information : id password name birthday phone_number email wage company
    Button changeComp,editButton;
    TextView nameView,companyView,birthdayView,phoneView,emailView,wageView;
    EditText nameEdit,phoneEdit,emailEdit,wageEdit;
    DatePicker birthdayEdit;
    Intent o,g;
    SharedPreferences pref;
    boolean isEditing=false;
    @Override
    public void onCreate(Bundle saveInstanceBundle){

        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_profile);
        nameView=findViewById(R.id.nameView);
        companyView=findViewById(R.id.companyView);
        birthdayView=findViewById(R.id.birthView);
        phoneView=findViewById(R.id.phoneView);
        emailView=findViewById(R.id.emailView);
        wageView=findViewById(R.id.wageView);
        nameEdit=findViewById(R.id.nameEdit);
        birthdayEdit=findViewById(R.id.birthEdit);
        phoneEdit=findViewById(R.id.phoneEdit);
        emailEdit=findViewById(R.id.emailEdit);
        wageEdit=findViewById(R.id.wageEdit);
        editButton=findViewById(R.id.editBtn);
        changeComp=findViewById(R.id.changeCompBtn);
        changeComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(Profile.this,CompanyChanger.class);
                startActivityForResult(o,0);
            }
        });
        pref=getSharedPreferences("user",MODE_PRIVATE);
        nameView.setText(pref.getString("name","null"));
        companyView.setText(pref.getString("company","null"));
        birthdayView.setText(pref.getString("birthday","null"));
        phoneView.setText(pref.getString("phone","null"));
        emailView.setText(pref.getString("email","null"));
        wageView.setText(pref.getString("wage","null"));
        nameEdit.setText(pref.getString("name","null"));
        phoneEdit.setText(pref.getString("phone","null"));
        emailEdit.setText(pref.getString("email","null"));
        wageEdit.setText(pref.getString("wage","null"));
        String date=pref.getString("birth","1900-01-01");
        String[] dateField=date.split("-");
        birthdayEdit.updateDate(Integer.valueOf(dateField[0]),Integer.valueOf(dateField[1]),Integer.valueOf(dateField[2]));
        NetworkManager nw=new NetworkManager(getString(R.string.serverURL));
        nw.in.addItem("id",pref.getString("id","null"));
        nw.in.addItem("password",pref.getString("password","null"));
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition((ViewGroup)findViewById(R.id.profileLayout));
                if(isEditing){
                    nameView.setVisibility(VISIBLE);
                    birthdayView.setVisibility(VISIBLE);
                    phoneView.setVisibility(VISIBLE);
                    emailView.setVisibility(VISIBLE);
                    wageView.setVisibility(VISIBLE);
                    nameEdit.setVisibility(GONE);
                    birthdayEdit.setVisibility(GONE);
                    phoneEdit.setVisibility(GONE);
                    emailEdit.setVisibility(GONE);
                    wageEdit.setVisibility(GONE);
                    isEditing=false;
                }else {
                    nameView.setVisibility(GONE);
                    birthdayView.setVisibility(GONE);
                    phoneView.setVisibility(GONE);
                    emailView.setVisibility(GONE);
                    wageView.setVisibility(GONE);
                    nameEdit.setVisibility(VISIBLE);
                    birthdayEdit.setVisibility(VISIBLE);
                    phoneEdit.setVisibility(VISIBLE);
                    emailEdit.setVisibility(VISIBLE);
                    wageEdit.setVisibility(VISIBLE);
                    isEditing=true;
                }
            }
        });

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
