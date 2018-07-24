package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    //TODO information : id password name birthday phone_number email wage
    Intent o,g;
    EditText idEdit,passEdit,nameEdit,phoneEdit,emailEdit,wageEdit;
    DatePicker ageEdit;
    Button cancelBtn,signUpBtn;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_sign_up);
        g=getIntent();
        idEdit=findViewById(R.id.idEdit);
        passEdit=findViewById(R.id.passEdit);
        nameEdit=findViewById(R.id.nameEdit);
        ageEdit=findViewById(R.id.ageEdit);
        cancelBtn=findViewById(R.id.cancelBtn);
        signUpBtn=findViewById(R.id.signUpBtn);
        phoneEdit=findViewById(R.id.phoneEdit);
        emailEdit=findViewById(R.id.emailEdit);
        wageEdit=findViewById(R.id.wageEdit);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO send id,name,password,age to server for sign up
                SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                String id=idEdit.getText().toString(),password=passEdit.getText().toString(),name=nameEdit.getText().toString();
                String birth=ageEdit.getYear()+"-"+(ageEdit.getMonth()+1)+"-"+ageEdit.getDayOfMonth();
                String email=emailEdit.getText().toString(),wage=wageEdit.getText().toString(),phone=phoneEdit.getText().toString();

                editor.putString("id",id);
                editor.putString("pass",password);
                editor.putString("name",name);
                editor.putString("birth",birth);
                editor.putString("email",email);
                editor.putString("wage",wage);
                editor.putString("phone",phone);
                editor.putBoolean("isUsing",true);
                editor.commit();
                String result="null";
                NetworkManager nm=new NetworkManager(getString(R.string.serverURL));
                
                nm.in.addItem("id",id);
                nm.in.addItem("password",password);
                nm.in.addItem("name",name);
                nm.in.addItem("birthday",birth);
                nm.in.addItem("email",email);
                nm.in.addItem("wage",wage);
                nm.in.addItem("phone",phone);
                nm.execute();
                o=new Intent(SignUp.this,MainActivity.class);

                startActivity(o);
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
}
