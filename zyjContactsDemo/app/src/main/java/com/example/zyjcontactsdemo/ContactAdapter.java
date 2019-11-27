package com.example.zyjcontactsdemo;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {
	List<ContactBean> items = new ArrayList<ContactBean>();
	private Context context;

	public ContactAdapter(Context context, List<ContactBean> list) {
		this.items = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(
				R.layout.adapter_contact, parent,false);
		TextView name_tv = (TextView) convertView.findViewById(R.id.name_tv);
		name_tv.setText(items.get(position).name);
		return convertView;
	}

	// ===================================================
	@Override
	public int getPositionForSection(int section) {
		ContactBean bean;
		String l;
		if (section == '!') {
			return 0;
		} else {
			for (int i = 0; i < getCount(); i++) {
				bean = (ContactBean) items.get(i);
				// 取首字母
				l = FirstLetterUtil.getFirstLetter(bean.name);
				char firstChar = l.toUpperCase().charAt(0);
				if (firstChar == section) {
					return i;
				}
			}
		}
		bean = null;
		l = null;
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

}
