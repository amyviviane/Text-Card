package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Test_pic extends AppCompatActivity {

    //題目圖片
    ImageView pic;

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
        setContentView(R.layout.activity_test_pic);

        pic = (ImageView) findViewById(R.id.imageView11);
        c1 = (ImageButton) findViewById(R.id.imageView12);
        c2 = (ImageButton) findViewById(R.id.imageView13);
        c3 = (ImageButton) findViewById(R.id.imageView14);
        c4 = (ImageButton) findViewById(R.id.imageView15);
        t1 = (TextView) findViewById(R.id.textView9);
        t2 = (TextView) findViewById(R.id.textView10);
        t3 = (TextView) findViewById(R.id.textView11);
        t4 = (TextView) findViewById(R.id.textView12);
        c = (TextView) findViewById(R.id.textView20);
        next = (ImageButton) findViewById(R.id.imageButton2);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final int total = extras.getInt("total");
        final int count = extras.getInt("count");

        c.setText(count);

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
        int i = (int) (Math.random() * 4);
        switch (i) {
            case 0:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total", x);
                extra.putInt("count", y + 1);

                intent = new Intent(context, Test_pic.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 1:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total", x);
                extra.putInt("count", y + 1);

                intent = new Intent(context, Test_sound.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 2:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total", x);
                extra.putInt("count", y + 1);

                intent = new Intent(context, Test_word.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
        }
    }
}
