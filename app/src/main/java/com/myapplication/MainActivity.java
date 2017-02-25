package com.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearSnapHelper linearSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private View snapView;
    private DemoAdapter demoAdapter;
    private int mCurrentItemOffset;
    private int total;
    private float screenHalf = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        demoAdapter = new DemoAdapter(this);
        recyclerView.setAdapter(demoAdapter);
        linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);
        total = demoAdapter.getItemCount() * 160;
        screenHalf = UiUtils.getScreenWidthPixels(this) / 2;

//      recyclerView.scrollToPosition(5);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int position;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ArrayList<View> views = getVisibleViews();
                for (int i = 0; i < views.size(); i++) {
                    View view = views.get(i);
//                  view.getLocationInWindow(pos);
                    float leftDis = view.getLeft();
                    int right = view.getRight();

                    float rightDis = (screenHalf * 2 - right);
                    float distance = Math.min(leftDis, rightDis) + 100f;

                    float scale = distance / screenHalf;

                    view.setScaleX(scale);
                    view.setScaleY(scale);



//                  Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 90, x, pos[1], 400, true);
//                  view.startAnimation(rotate3dAnimation);
                }
//                mCurrentItemOffset += dx;
//                Log.d("MainActivity", "mCurrentItemOffset:" + mCurrentItemOffset);
//
//                int i = mCurrentItemOffset + screenHalf / screenHalf;

//                View next = linearLayoutManager.findViewByPosition(position);
////                View prev = linearLayoutManager.findViewByPosition(position - 1);
//
//                if (next != null) {
//
//                }
//                prev.setScaleX(0.8f);
//                prev.setScaleY(0.8f);

            }
        });


    }

    private ArrayList<View> getVisibleViews() {
        ArrayList<View> views = new ArrayList<>();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            views.add(linearLayoutManager.findViewByPosition(i));
        }

        return views;

    }
}
