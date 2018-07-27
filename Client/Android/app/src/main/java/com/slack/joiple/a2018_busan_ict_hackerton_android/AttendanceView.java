package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AttendanceView extends AppCompatActivity {
    LinearLayout resultLayout;
    EditText startDate,startTime,endDate,endTime;
    Button searchBtn;
    @Override
    public void onCreate(Bundle saveInstanceBundle){
        super.onCreate(saveInstanceBundle);
        this.setContentView(R.layout.activity_attendence_view);
        resultLayout=findViewById(R.id.resultLayout);
        startDate=findViewById(R.id.fromDate);
        startTime=findViewById(R.id.fromTime);
        endDate=findViewById(R.id.toDate);
        endTime=findViewById(R.id.toTime);
        searchBtn=findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkManager nm=new NetworkManager(getString(R.string.serverURL),"post");//TODO set proper action
                nm.in.addItem("startDate",startDate.getText().toString());
                nm.in.addItem("startTime",startTime.getText().toString());
                nm.in.addItem("endDate",endDate.getText().toString());
                nm.in.addItem("endTime",endTime.getText().toString());
                //nm.execute();
                int num=Integer.parseInt(nm.out.getItem("number"));
                for(int i=0;i<num;i++){
                    LinearLayout tmpLayout=new LinearLayout(AttendanceView.this);
                    tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
                    tmpLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                    TextView times=new TextView(AttendanceView.this);
                    times.setText(nm.out.getItem("date"+i)+" "+nm.out.getItem("time"+i));
                    times.setGravity(Gravity.START);
                    times.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
                    TextView events=new TextView(AttendanceView.this);
                    events.setText(nm.out.getItem("event"+i));
                    events.setGravity(Gravity.END);
                    events.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1));
                    tmpLayout.addView(times);
                    tmpLayout.addView(events);
                    resultLayout.addView(tmpLayout);
                }
            }
        });
    }
}
