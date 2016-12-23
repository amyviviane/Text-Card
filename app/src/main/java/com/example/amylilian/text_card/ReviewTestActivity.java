package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class ReviewTestActivity extends AppCompatActivity {

    //add imagebutton
    ImageButton imageButton1_1;
    ImageButton imageButton1_2;

    //add intent
    private Context context;
    private Intent intent;

    String[] activity_name;
    int x;
    String y;

    //add category number
    String[] category;
    int c_number;

    //add Bundle
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);

        //ActionBar to be center
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_review_test);

        imageButton1_1 = (ImageButton) findViewById(R.id.test_imageButton1_1);
        imageButton1_2 = (ImageButton) findViewById(R.id.test_imageButton1_2);

        activity_name = getResources().getStringArray(R.array.activity_name);

        category = getResources().getStringArray(R.array.category_number);

        context = this;
        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_1.setImageResource(R.drawable.btm_group01_b);
                c_number = Integer.parseInt(category[0]);
                //trans(c_number);
            }
        });
        imageButton1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_2.setImageResource(R.drawable.btm_group01_b);
                c_number = Integer.parseInt(category[1]);
                //trans(c_number);
            }
        });

    }

    //i == tatal
    public void trans(int i) {
        x = (int) (Math.random() * 4);
        switch (x){
            case 0:
                //package
                extra = new Bundle();
                extra.putInt("total",i);

                //send
                intent = new Intent(context , Test_pic.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 1:
                //package
                extra = new Bundle();
                extra.putInt("total",i);

                //send
                intent = new Intent(context , Test_sound.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
            case 2:
                //package
                extra = new Bundle();
                extra.putInt("total",i);
                extra.putInt("count",1);

                //send
                intent = new Intent(context , Test_word.class);
                intent.putExtras(extra);
                startActivity(intent);
                break;
        }
    }
}
