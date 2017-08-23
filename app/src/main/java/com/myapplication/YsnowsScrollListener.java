package com.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 定制RecyclerView滑动中子Views的缩放和翻滚
 * <p>
 * Created by xianguangjin on 2017/2/27.
 */

public class YsnowsScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private float screenHalf = 0;//屏幕一半的宽度
    private LinearSnapHelper linearSnapHelper;
    public static float viewWidth = 200f;//itemView的宽度
    private float initDegree = 180f / viewWidth;//0~200,每段距离要走几度
    private OnPositionSelectedListener onPositionSelectedListener;//最终位置选中监听器
    private int totalScrollX = 0;//x轴总共滑动的距离;

    public YsnowsScrollListener(RecyclerView recyclerView, LinearSnapHelper linearSnapHelper) {
        super();
        this.recyclerView = recyclerView;
        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        screenHalf = UiUtils.getScreenWidthPixels(recyclerView.getContext()) / 2;
        this.linearSnapHelper = linearSnapHelper;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (onPositionSelectedListener != null) {
                View view = linearSnapHelper.findSnapView(linearLayoutManager);
                int pos = linearLayoutManager.getPosition(view);
                onPositionSelectedListener.onSelected(pos);
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        totalScrollX += dx;
        ArrayList<View> views = getVisibleViews();
        for (int i = 0; i < views.size(); i++) {
            View view = views.get(i);
            float leftDis = view.getLeft();
            int right = view.getRight();

            //计算缩放
            float rightDis = (screenHalf * 2 - right);
            float distance = Math.min(leftDis, rightDis) + viewWidth / 2;
            float initRate = distance / screenHalf;
            float scale = Math.abs((1 - initRate) * 0.5f - 1);

            view.setScaleX(scale);
            view.setScaleY(scale);

            if (leftDis > screenHalf - viewWidth * 3 / 2 && leftDis < screenHalf + viewWidth / 2) {//只有中间的两个可以翻转
                //计算角度
                Object tag = view.getTag();
                YsnowsBaseModel ysnowsBaseModel = null;
                if (tag != null && tag instanceof YsnowsBaseModel) {
                    ysnowsBaseModel = (YsnowsBaseModel) tag;
                }
                float distanceA = Math.abs((distance - screenHalf) % viewWidth);
                if (distanceA > viewWidth / 2) {
                    distanceA = viewWidth - distanceA;
                    if (ysnowsBaseModel != null) {
                        ((TextView) view).setText(ysnowsBaseModel.getContent()[0]);
                    }
                } else {
                    if (ysnowsBaseModel != null) {
                        ((TextView) view).setText(ysnowsBaseModel.getContent()[1]);
                    }
                }
                float degree = distanceA * initDegree;
                view.setRotationY(degree);
            } else {
                view.setRotationY(0);
            }
        }


    }

    /**
     * 获取所有可见的子Views
     *
     * @return 所有可见的子Views
     */
    private ArrayList<View> getVisibleViews() {
        ArrayList<View> views = new ArrayList<>();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

        for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
            views.add(linearLayoutManager.findViewByPosition(i));
        }

        return views;
    }

    /**
     * 选中某个position到中间
     *
     * @param position
     */
    public void selectPosition(int position) {
        recyclerView.smoothScrollBy((int) (position * viewWidth - screenHalf), 0);
    }


    /**
     * 获取最终选中的position
     *
     * @return
     */
    public int getSelectedPosition() {
        return (int) ((totalScrollX + screenHalf) / viewWidth);
    }

    /**
     * 添加位置最终滑动监听器
     *
     * @param onPositionSelectedListener
     */
    public void addOnPositionSelectedListener(OnPositionSelectedListener onPositionSelectedListener) {
        this.onPositionSelectedListener = onPositionSelectedListener;
    }

    /**
     * 选中监听器
     */
    interface OnPositionSelectedListener {
        void onSelected(int pos);
    }

}

