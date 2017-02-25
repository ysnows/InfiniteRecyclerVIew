package com.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class ScrollViewActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        tvContent = (TextView) findViewById(R.id.tv_content);

        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(80, 180, v.getPivotX(), v.getPivotY(), 0, true);
//                rotateAnimation.setDuration(1000);
//                tvContent.startAnimation(rotateAnimation);

                v.setRotationY(40f);
            }
        });
    }

}
