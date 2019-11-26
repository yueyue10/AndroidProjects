package com.passion.zyj.knowall.ui.tools.fooddt;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 食材列表适配器
 *
 * @author quchao
 * @date 2018/2/24
 */
public class MaterialsAdapter extends BaseQuickAdapter<String, MaterialsAdapter.ViewHolder> {

    public MaterialsAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, String item) {
        helper.textView.setText(item);
        helper.textView.setTextSize(13);
        helper.textView.setGravity(Gravity.LEFT);
        helper.textView.setTextColor(Color.parseColor("#5C5C5E"));
        helper.textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        helper.textView.setPadding(0, 0, 0, ScreenUtils.dip2px(mContext, 5));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.textView)
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}