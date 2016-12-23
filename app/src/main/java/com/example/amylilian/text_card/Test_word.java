package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Test_word extends AppCompatActivity {

    //題目文字
    TextView text;

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

    //add intent
    private Context context;
    private Intent intent;

    ImageView nextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_word);

        text = (TextView) findViewById(R.id.textView2);
        c1 = (ImageButton) findViewById(R.id.imageView7);
        c2 = (ImageButton) findViewById(R.id.imageView8);
        c3 = (ImageButton) findViewById(R.id.imageView9);
        c4 = (ImageButton) findViewById(R.id.imageView10);
        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView4);
        t3 = (TextView) findViewById(R.id.textView7);
        t4 = (TextView) findViewById(R.id.textView8);
        nextpage = (ImageView) findViewById(R.id.nextpage_imgbun);

        //get bundle
        Bundle extras = getIntent().getExtras();
        final int total = extras.getInt("total");
        final int count = extras.getInt("count");

        c.setText(count);

        if (total == count){
            nextpage.setImageResource(R.drawable.btm_end_a);
            nextpage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextpage.setImageResource(R.drawable.btm_end_b);
                    intent = new Intent(context , Test_End_Activity.class);
                    startActivity(intent);
                }
            });
        }else{
            nextpage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextpage.setImageResource(R.drawable.btm_right_b);
                    trans(total,count);
                }
            });
        }
    }

    //x=total ; y= new count
    public void trans(int x,int y) {
        Bundle extra;
        int i = (int) (Math.random() * 4);
        switch (i){
            case 0:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total",x);
                extra.putInt("count",y + 1);

                intent = new Intent(context , Test_pic.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 1:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total",x);
                extra.putInt("count",y + 1);

                intent = new Intent(context , Test_sound.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 2:
                //add Bundle
                extra = new Bundle();
                //package
                extra.putInt("total",x);
                extra.putInt("count",y + 1);

                intent = new Intent(context , Test_word.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
        }
    }
}
