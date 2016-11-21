
package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StudyActivity extends AppCompatActivity {

    //add imagebutton
    ImageButton imageButton1_1;
    ImageButton imageButton1_2;

    //add intent
    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        imageButton1_1 = (ImageButton) findViewById(R.id.imageButton1_1);
        imageButton1_2 = (ImageButton) findViewById(R.id.imageButton1_2);

        imageButton1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_1.setImageResource(R.drawable.btm_group01_b);

                intent = new Intent(context , StudyContentActivity.class);
                startActivity(intent);
            }
        });
        imageButton1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButton1_2.setImageResource(R.drawable.btm_group02_b);

                intent = new Intent(context , StudyContentActivity.class);
                startActivity(intent);
            }
        });

    }
}
