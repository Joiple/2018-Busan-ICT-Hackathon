package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Intent o,g;
    Button register,start,end,option,details;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_main);
        g=getIntent();
        register=findViewById(R.id.changeCompBtn);
        start=findViewById(R.id.workBtn);
        end=findViewById(R.id.offBtn);
        details=findViewById(R.id.detailBtn);
        option=findViewById(R.id.optionBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,ChangeCompany.class);
                startActivity(o);
                finish();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true){//TODO get setting of main -true : qr
                    o=new Intent(MainActivity.this,QrReader.class);
                    startActivity(o);
                    finish();
                }else{
                    o=new Intent(MainActivity.this,NfcTagging.class);
                    startActivity(o);
                    finish();
                }
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true){//TODO get setting of main -true : qr
                    o=new Intent(MainActivity.this,QrReader.class);
                    startActivity(o);
                    finish();
                }else{
                    o=new Intent(MainActivity.this,NfcTagging.class);
                    startActivity(o);
                    finish();
                }
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,Profile.class);
                startActivity(o);
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(MainActivity.this,Option.class);
                startActivity(o);
            }
        });
    }
    @Override
    protected void onActivityResult(int request,int result,Intent data){
        if(data.getAction().equals("logout")){
            o=new Intent(MainActivity.this,SignIn.class);
            startActivity(o);
            finish();
        }
    }
}
