package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText idEdit,passEdit;
    TextView msgView;
    Button signIn,signUp;
    Intent o,g;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_sign_in);
        g=getIntent();
        idEdit=findViewById(R.id.idEdit);
        passEdit=findViewById(R.id.passEdit);
        msgView=findViewById(R.id.msg);
        signIn=findViewById(R.id.logInBtn);
        signUp=findViewById(R.id.signUpBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o=new Intent(SignIn.this,SignUp.class);
                startActivityForResult(o,0);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=idEdit.getText().toString();
                String password=passEdit.getText().toString();
                NetworkManager nw=new NetworkManager(getString(R.string.serverURL),"login");
                nw.in.addItem("id",id);
                nw.in.addItem("password",password);
                nw.execute();


                if(nw.out.getItem("check").equals("true")){//TODO get check value
                //TODO get login result
                if(true){//login result
                    SharedPreferences pref=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=pref.edit();
                    editor.putBoolean("isUsing",true);
                    //TODO get user Info
                    editor.putString("id",idEdit.getText().toString());
                    editor.putString("pass",passEdit.getText().toString());
                    editor.commit();
                    o=new Intent(SignIn.this,MainActivity.class);
                    startActivity(o);
                    setResult(RESULT_OK);
                    finish();
                }else{
                    TransitionManager.beginDelayedTransition((ViewGroup)findViewById(R.id.textLayout));
                    msgView.setText("reason that why you can't sign in will be here");
                    msgView.setVisibility(View.VISIBLE);
                }
            }
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
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "종료하실려면 한번더 뒤로가기를 누르세요.", Toast.LENGTH_SHORT).show();
        }
    }
}
