package com.slack.joiple.a2018_busan_ict_hackerton_android;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustCardView extends ConstraintLayout {
    TextView title,main,sub,date;
    View accent;
    public CustCardView(Context context) {
        super(context);
        initView();
    }

    public CustCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public CustCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs,defStyleAttr);
    }
    void initView(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.card_layout, this, false);
        addView(v);
        title=(TextView)findViewById(R.id.subject);
        main=(TextView)findViewById(R.id.contentMain);
        sub=(TextView)findViewById(R.id.contentSub);
        date=(TextView)findViewById(R.id.date);
        accent=findViewById(+R.id.colorbox);

    }
    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustCardView);
        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustCardView, defStyle, 0);
        setTypeArray(typedArray);

    }
    private void setTypeArray(TypedArray typedArray) {


        int bg_resID = typedArray.getResourceId(R.styleable.CustCardView_bgColor,R.color.white);
        this.setBackgroundResource(bg_resID);

        int color_resID = typedArray.getResourceId(R.styleable.CustCardView_color,R.color.Btn);
        accent.setBackgroundResource(color_resID);

        String title=typedArray.getString(R.styleable.CustCardView_title);
        String main=typedArray.getString(R.styleable.CustCardView_mainContent);
        String sub=typedArray.getString(R.styleable.CustCardView_subContent);
        String date=typedArray.getString(R.styleable.CustCardView_date);
        boolean size=typedArray.getBoolean(R.styleable.CustCardView_size,true);

        int textColor =
        text.setTextColor(textColor);

        String text_string = typedArray.getString(R.styleable.LoginButton_text);
        text.setText(text_string);


        typedArray.recycle();

    }

}
