package com.charlie.mpandroidcharttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ColorUtils;

import java.util.List;

public class MainAdapter extends BaseAdapter {

    List<MainBean> data;
    Context mContext;

    public MainAdapter(List<MainBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_grid, null);
        LinearLayout root_layout = view.findViewById(R.id.root_layout);
        TextView textView = view.findViewById(R.id.textView1);
        textView.setText(data.get(position).getName());
        if (data.get(position).getName().contains("测试")) {
            root_layout.setBackgroundColor(ColorUtils.string2Int("#13C2B8"));
        } else {
            root_layout.setBackgroundColor(ColorUtils.string2Int("#FFFFFF"));
        }
        return view;
    }
}
