package com.example.amylilian.text_card;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton u;
    ImageView im;
    TextView t;
    int i;
    MediaPlayer m = new MediaPlayer();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u = (ImageButton) findViewById(R.id.imageView17);
        t = (TextView) findViewById(R.id.textView36);
        im = (ImageView) findViewById(R.id.imageView24);

        context = this;
        im.setVisibility(View.INVISIBLE);

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.isPlaying()){

                }else {
                    i = (int) (Math.random() * 11 + 1);
                    im.setVisibility(View.INVISIBLE);
                    if (i == 1) {
                        t.setText("魚蠢的normal people");
                        m = MediaPlayer.create(context, R.raw.s5);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.angry);
                    } else if (i == 2) {
                        t.setText("本大爺肚子餓了！");
                        m = MediaPlayer.create(context, R.raw.s2);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.can);
                    } else if (i == 3) {
                        t.setText("一天一apple，doctor遠離我");
                        m = MediaPlayer.create(context, R.raw.s2);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.apple);
                    } else if (i == 4) {
                        t.setText("多多背單字有益身體健康");
                        m = MediaPlayer.create(context, R.raw.s1);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.heart);
                    } else if (i == 5) {
                        t.setText("喵喵喵喵喵");
                        m = MediaPlayer.create(context, R.raw.s4);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.meatball);
                    } else if (i == 6) {
                        t.setText("好久沒來了，孤單寂寞……覺得冷");
                        m = MediaPlayer.create(context, R.raw.s6);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.sweat);
                    } else if (i == 7) {
                        t.setText("Love you every day");
                        m = MediaPlayer.create(context, R.raw.s4);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.heart);
                    } else if (i == 8) {
                        t.setText("本大爺才不是喵星人呢，汪汪");
                        m = MediaPlayer.create(context, R.raw.s3);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.meatball);
                    } else if (i == 9) {
                        t.setText("把你的手拿開！放開本大爺！");
                        m = MediaPlayer.create(context, R.raw.s5);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.shock);
                    } else if (i == 10) {
                        t.setText("好久沒有進貢罐罐了，真不應該～");
                        m = MediaPlayer.create(context, R.raw.s6);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.can);
                    } else if (i == 11) {
                        t.setText("摸夠了也該快點去學習新單字啦～");
                        m = MediaPlayer.create(context, R.raw.s1);
                        m.start();
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(R.drawable.shock);
                    }
                }
            }
        });

//        //只能直立
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*delete flat button in app_bar_main.xml
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


//    @Override
//   public void onBackPressed() {
//
//    }


    /*delete option menu(settings)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    //側邊欄
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Study) {
            Intent intent = new Intent(this , StudyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ReviewTest) {
            Intent intent = new Intent(this , ReviewTestActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_About) {
            Intent intent = new Intent(this , AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
