// Tyler Cadeau
// 200246900
// 2016/04/18
// Splash.java is the splash screen which displays my logo with a simple animation

package com.example.human.caloriecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Human on 2016-04-07.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //Grab Logo
        final ImageView logo = (ImageView) findViewById(R.id.imageView);
        //Animation
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        //Fade
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        logo.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Start animation
                logo.startAnimation(animation2);
                //Finish
                finish();
                //Declare intent and start main screen
                Intent i = new Intent(Splash.this, CalorieCountScreen.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
