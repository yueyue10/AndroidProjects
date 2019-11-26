package com.passion.zyj.knowall.ui.tools;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.passion.zyj.knowall.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 景点标签列表适配器
 *
 * @author quchao
 * @date 2018/2/24
 */
public class FoodTagAdapter extends BaseQuickAdapter<String, FoodTagAdapter.ViewHolder> {

    public FoodTagAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, String item) {
        helper.spot_tag_tv.setText(item);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.spot_tag_tv)
        TextView spot_tag_tv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
