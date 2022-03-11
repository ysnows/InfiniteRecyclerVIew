package com.myapplication;

import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xianguangjin on 2017/2/24.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.VH> {
    private Context context;
    private RecyclerView.LayoutParams layoutParams;
    private ArrayList<YsnowsBaseModel> data = new ArrayList();

    public DemoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        return new VH(tv);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        YsnowsBaseModel demoModel = data.get(position);

        //两边的itemview设置margin
        if (position == 0) {
            layoutParams.setMarginStart((int) (YsnowsScrollListener.viewWidth / 2));
            layoutParams.setMarginEnd(0);
        } else if (position == data.size() - 1) {
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd((int) (YsnowsScrollListener.viewWidth / 2));
        } else {
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
        }

        holder.itemView.setLayoutParams(layoutParams);
        TextView tv = (TextView) holder.itemView;
        tv.setText(demoModel.getContent()[0]);
        holder.itemView.setTag(demoModel);

        //根据model的type设置背景
        if (demoModel.getType() == YsnowsBaseModel.TYPE_FUTURE) {
            holder.itemView.setBackgroundResource(R.drawable.cirlce);
        } else if (demoModel.getType() == YsnowsBaseModel.TYPE_PLACEHOLDER) {
            tv.setText("");
            holder.itemView.setBackgroundResource(R.drawable.cirlce_gray);
        } else if (demoModel.getType() == YsnowsBaseModel.TYPE_CURRENT) {
            holder.itemView.setBackgroundResource(R.drawable.cirlce_deep);
        } else if (demoModel.getType() == YsnowsBaseModel.TYPE_PASTED) {
            holder.itemView.setBackgroundResource(R.drawable.cirlce_gray_deep);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        public VH(View itemView) {
            super(itemView);
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams((int) YsnowsScrollListener.viewWidth, (int) YsnowsScrollListener.viewWidth);
            itemView.setLayoutParams(layoutParams);
        }
    }

    public void setData(ArrayList<YsnowsBaseModel> models) {
        data.clear();
        data.addAll(models);
    }
}
