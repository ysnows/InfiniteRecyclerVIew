package com.myapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearSnapHelper linearSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private DemoAdapter demoAdapter;
    private YsnowsScrollListener ysnowsScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //设置recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        demoAdapter = new DemoAdapter(this);
        recyclerView.setAdapter(demoAdapter);
        ArrayList<YsnowsBaseModel> demoModels = new ArrayList<>();

        demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_PLACEHOLDER, "", ""));
        demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_PLACEHOLDER, "", ""));
        for (int i = 1; i < 8; i++) {

            if (i < 4) {
                demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_PASTED, i + "", "第" + i + "天"));
            } else if (i == 4) {
                demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_CURRENT, i + "", "今天"));
            } else {
                demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_FUTURE, i + "", "第" + i + "天"));
            }
        }
        demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_PLACEHOLDER, "", ""));
        demoModels.add(new YsnowsBaseModel(YsnowsBaseModel.TYPE_PLACEHOLDER, "", ""));
        demoAdapter.setData(demoModels);

        //添加LinearSnapHelper
        linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);

        //添加滑动监听器
        ysnowsScrollListener = new YsnowsScrollListener(recyclerView);
        recyclerView.addOnScrollListener(ysnowsScrollListener);
        ysnowsScrollListener.addOnPositionSelectedListener(new YsnowsScrollListener.OnPositionSelectedListener() {
            @Override
            public void onSelected(int pos) {
//                Toast.makeText(MainActivity.this, "pos:" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        ysnowsScrollListener.selectPosition(1 + 2);
    }
}
