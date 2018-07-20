package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    Intent i;
    EditText idEdit,passEdit,nameEdit,ageEdit;
    Button cancelBtn,signUpBtn;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_sign_up);
        idEdit=findViewById(R.id.idEdit);
        passEdit=findViewById(R.id.passEdit);
        nameEdit=findViewById(R.id.nameEdit);
        ageEdit=findViewById(R.id.ageEdit);
        cancelBtn=findViewById(R.id.cancelBtn);
        signUpBtn=findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idEdit.getText().toString(),password=passEdit.getText().toString(),name=nameEdit.getText().toString();
                int age=Integer.getInteger(ageEdit.getText().toString());
                //TODO send id,name,password,age to server for sign up
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
