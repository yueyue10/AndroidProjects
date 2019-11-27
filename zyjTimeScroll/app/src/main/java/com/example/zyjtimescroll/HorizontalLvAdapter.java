package com.example.zyjtimescroll;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HorizontalLvAdapter extends BaseAdapter {
	private Context context;
	private List<DataInfo> datas = new ArrayList<DataInfo>();

	public HorizontalLvAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.adapter_huiyidetail_horlv, null);
		TextView horlv_item_tv = (TextView) convertView
				.findViewById(R.id.horlv_item_tv);
		horlv_item_tv.setText(datas.get(position).datainfo);
		return convertView;
	}

	public void LoadData(List<DataInfo> lsit) {
		datas.clear();
		datas.addAll(lsit);
		notifyDataSetChanged();
	}
}
