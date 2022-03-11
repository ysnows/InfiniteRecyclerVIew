package com.myapplication;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
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

        int emuiLeval = UiUtils.getEmuiLeval();
        Log.d(TAG, "__" + emuiLeval);
        Toast.makeText(this, "__" + emuiLeval, Toast.LENGTH_SHORT).show();

        //设置recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        demoAdapter = new DemoAdapter(this);
        recyclerView.setAdapter(demoAdapter);
        ArrayList<YsnowsBaseModel> demoModels = new ArrayList<>();

        //生成数据
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
        ysnowsScrollListener = new YsnowsScrollListener(recyclerView, linearSnapHelper);
        recyclerView.addOnScrollListener(ysnowsScrollListener);
        ysnowsScrollListener.addOnPositionSelectedListener(new YsnowsScrollListener.OnPositionSelectedListener() {
            @Override
            public void onSelected(int pos) {
//                linearSnapHelper.findTargetSnapPosition(linearLayoutManager,)
                Log.d("TAG", pos + "");

            }
        });

        ysnowsScrollListener.selectPosition(4 + 2);
    }
}
