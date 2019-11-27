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

public class DragDelAdapter extends RecyclerView.Adapter<DragDelAdapter.ViewHolder> {

    private List<Subject> datas;
    private Context mContext;
    private LayoutInflater mLiLayoutInflater;

    public DragDelAdapter(List<Subject> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
        this.mLiLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public DragDelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLiLayoutInflater.inflate(R.layout.item_dragdel, parent, false));
    }

    @Override
    public void onBindViewHolder(DragDelAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.img.setImageResource(datas.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView img;
        public LinearLayout ll_item;
        LinearLayout ll_hidden;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);

            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            ll_hidden = (LinearLayout) itemView.findViewById(R.id.ll_hidden);
        }
    }
}
