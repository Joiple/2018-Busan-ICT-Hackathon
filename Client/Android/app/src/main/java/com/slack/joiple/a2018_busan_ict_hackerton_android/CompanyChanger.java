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
import android.widget.Toast;

public class CompanyChanger extends AppCompatActivity {
    Intent o, g;
    ImageButton menu, logout, option, logofont;
    EditText compEdit;
    Button submit, cancel, add;
    TextView result;
    SharedPreferences user, pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle saveInstanceBundle) {
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_change_company);
        user = getSharedPreferences("user", MODE_PRIVATE);
        compEdit = findViewById(R.id.compEdit);
        submit = findViewById(R.id.sendBtn);
        result = findViewById(R.id.resultView);
        cancel = findViewById(R.id.cancelBtn);
        add = findViewById(R.id.addBtn);
        pref = getSharedPreferences("user", MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO send change action to server
                if (true) {//TODO result of action
                    Toast.makeText(CompanyChanger.this, "Your company was successfully changed.", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = pref.edit();
                    //TODO modify company info into sharedPreferences
                    setResult(RESULT_OK);
                } else {
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o = new Intent(CompanyChanger.this, CompanyAdder.class);
                startActivityForResult(o, 10);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = user.edit();
                editor.putBoolean("isUsing", false);
                editor.commit();
                o = new Intent(CompanyChanger.this, SignIn.class);
                startActivity(o);
                finish();
            }
        });
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                o = new Intent(CompanyChanger.this, Option.class);
                startActivityForResult(o, 0);
            }
        });

    }
        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data) {
            if (requestCode == 10) {
                if (resultCode == RESULT_OK) {
                    finish();
                }
            }
        }
        @Override
        public void onBackPressed () {
            setResult(RESULT_CANCELED);
            super.onBackPressed();
        }
    }


