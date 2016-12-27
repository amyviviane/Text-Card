package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Test_sound extends AppCompatActivity {

    //題目聲音按鈕
    ImageView sound;

    //選項imageButton
    ImageButton c1;
    ImageButton c2;
    ImageButton c3;
    ImageButton c4;

    //選項textView
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;

    //now count
    TextView c;

    //ImageButton next page
    ImageButton next;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound);

        sound = (ImageView) findViewById(R.id.imageButton3);
        c1 = (ImageButton) findViewById(R.id.imageButton4);
        c2 = (ImageButton) findViewById(R.id.imageButton5);
        c3 = (ImageButton) findViewById(R.id.imageButton6);
        c4 = (ImageButton) findViewById(R.id.imageButton7);
        t1 = (TextView) findViewById(R.id.textView14);
        t2 = (TextView) findViewById(R.id.textView15);
        t3 = (TextView) findViewById(R.id.textView16);
        t4 = (TextView) findViewById(R.id.textView17);
        c = (TextView) findViewById(R.id.textView18);
        next = (ImageButton) findViewById(R.id.imageButton8);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final int total = extras.getInt("total");
        final int count = extras.getInt("count");

        c.setText(count + "");

        context = this;
        if (total == count){
            next.setImageResource(R.drawable.btm_end_a);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.setImageResource(R.drawable.btm_end_b);
                    intent = new Intent(context , Test_End_Activity.class);
                    startActivity(intent);
                }
            });
        }else{
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.setImageResource(R.drawable.btm_right_b);
                    trans(total,count);
                }
            });
        }
    }

    //x=total ; y= new count
    public void trans(int x,int y) {
        Bundle extra;
        int i = (int) (Math.random() * 3);
        //add Bundle
        extra = new Bundle();
        //package
        extra.putInt("total",x);
        extra.putInt("count",y + 1);
        switch (i){
            case 0:
                intent = new Intent(context , Test_pic.class);
                break;
            case 1:

                intent = new Intent(context , Test_sound.class);
                break;
            case 2:

                intent = new Intent(context , Test_word.class);
                break;
        }
        intent.putExtras(extra);
        startActivity(intent);
    }
}
