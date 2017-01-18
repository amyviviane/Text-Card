package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    //對錯的題數
    TextView e1;
    TextView e2;

    //ImageButton next page
    ImageButton next;

    //get bundle values
    int total;
    int count;
    String[] test_word;
    int correct;
    int wrong;

    //random number
    int rand;

    //ans
    int ans;

    //ans color
    String[] color;

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
        e1 = (TextView) findViewById(R.id.textView19);
        e2 = (TextView) findViewById(R.id.textView23);
        next = (ImageButton) findViewById(R.id.imageButton8);

        //get bundle
        Bundle extras = getIntent().getExtras();
        total = extras.getInt("total");
        count = extras.getInt("count");
        correct = extras.getInt("correct");
        wrong = extras.getInt("wrong");
        test_word = extras.getStringArray("test_word");

        c.setText(count + "");

        //random choose
        rand = (int) (Math.random() * 4);
        int[] ia = new int[3];
        int num = count + 2;
        for(int i = 0 ; i < 3 ; i++){
            if (num > total){
                num = num % total;
            }
            ia[i] = num;
            num++;
        }
        switch (rand) {
            case 0:
                t1.setText(test_word[count-1]);
                t2.setText(test_word[ia[0]-1]);
                t3.setText(test_word[ia[1]-1]);
                t4.setText(test_word[ia[2]-1]);
                break;
            case 1:
                t1.setText(test_word[ia[0]-1]);
                t2.setText(test_word[count - 1]);
                t3.setText(test_word[ia[1]-1]);
                t4.setText(test_word[ia[2]-1]);
                break;
            case 2:
                t1.setText(test_word[ia[0]-1]);
                t2.setText(test_word[ia[1]-1]);
                t3.setText(test_word[count - 1]);
                t4.setText(test_word[ia[2]-1]);
                break;
            case 3:
                t1.setText(test_word[ia[0]-1]);
                t2.setText(test_word[ia[1]-1]);
                t3.setText(test_word[ia[2]-1]);
                t4.setText(test_word[count - 1]);
                break;
        }

        //set color
        color = new String[4];
        for (int i = 0 ; i < 4 ; i++){
            if (i == rand){
                color[i] = "#6bfe63";
            }
            else {
                color[i] = "#ff1723";
            }
        }

        //show right and error number
        e1.setText("對：" + correct);
        e2.setText("錯：" + wrong);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 1;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 2;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 3;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = 4;
                if (ans == (rand + 1)){
                    correct++;
                }
                else {
                    wrong++;
                }
                anwser(color);
            }
        });

        context = this;
        if (total == count){
            next.setImageResource(R.drawable.btm_end_a);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.setImageResource(R.drawable.btm_end_b);
                    Bundle extra = new Bundle();
                    extra.putInt("correct",correct);
                    intent = new Intent(context , Test_End_Activity.class);
                    intent.putExtras(extra);
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
        extra.putStringArray("test_word",test_word);
        extra.putInt("correct",correct);
        extra.putInt("wrong",wrong);
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
    public void anwser(String[] s){

        t1.setTextColor(Color.parseColor(s[0]));
        t2.setTextColor(Color.parseColor(s[1]));
        t3.setTextColor(Color.parseColor(s[2]));
        t4.setTextColor(Color.parseColor(s[3]));

        //show right and error number
        e1.setText("對：" + correct);
        e2.setText("錯：" + wrong);
    }
}
