package com.smartdot.mywebview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartdot.mywebview.R;
import com.smartdot.mywebview.utils.recyclerview.Subject;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13 0013.
 * E-Mail：543441727@qq.com
 */

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> {

    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public DragAdapter(List<Subject> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public DragAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLiLayoutInflater.inflate(R.layout.item_drag, parent, false));
    }

    @Override
    public void onBindViewHolder(DragAdapter.ViewHolder holder, int position) {
        holder.drag_title.setText(datas.get(position).getTitle());
        holder.drag_img.setImageResource(datas.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drag_title;
        ImageView drag_img;

        public ViewHolder(View itemView) {
            super(itemView);
            drag_title = (TextView) itemView.findViewById(R.id.drag_title);
            drag_img = (ImageView) itemView.findViewById(R.id.drag_img);
        }
    }
}
