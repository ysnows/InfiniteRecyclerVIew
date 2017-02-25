package com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xianguangjin on 2017/2/24.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.VH> {
    private Context context;

    public DemoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_circle, null, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.tv.setText(position + "");
        holder.tv.setTag(position);

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class VH extends RecyclerView.ViewHolder {
        private final TextView tv;

        public VH(View itemView) {
            super(itemView);
            tv = ((TextView) itemView.findViewById(R.id.tv));
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(200, 200);
//            layoutParams.setMarginStart(20);
//            layoutParams.setMarginEnd(20);
            tv.setLayoutParams(layoutParams);
        }
    }
}
