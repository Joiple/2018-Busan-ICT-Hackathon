package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeCompany extends AppCompatActivity {
    Intent o,g;
    EditText compEdit;
    Button submit,cancel;
    TextView result;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_change_company);
        compEdit=findViewById(R.id.compEdit);
        submit=findViewById(R.id.sendBtn);
        result=findViewById(R.id.resultView);
        cancel=findViewById(R.id.cancelBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send change action to server
                if(true){//TODO result of action
                    Toast.makeText(ChangeCompany.this,"Your company was successfully changed.",Toast.LENGTH_SHORT).show();
                    o=new Intent(ChangeCompany.this,MainActivity.class);
                    startActivity(o);
                    finish();
                }else{
                    result.setVisibility(View.VISIBLE);
                    result.setText(getString(R.string.unavailableComp));
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
